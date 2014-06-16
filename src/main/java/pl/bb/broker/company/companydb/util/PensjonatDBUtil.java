package pl.bb.broker.company.companydb.util;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.bb.broker.company.companydb.pensjonat.entities.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class PensjonatDBUtil implements PensjonatDBReservationsUtil, PensjonatDBInvoicesUtil, PensjonatDBUserUtil {

    protected IHibernateUtil hibernateUtil = HibernateConfiguration.getSessionFactory();

    protected PensjonatDBUtil() {} //protected - only utils can use it.

    public void saveInvoice(InvoicesEntity invoice) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(invoice);
            tx.commit();
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
    }

    public void saveRooms(List<RoomsEntity> rooms) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            for(RoomsEntity room : rooms) {
                session.merge(room);
            }
            tx.commit();
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
    }

    public void saveUpdateFacility(FacilitiesEntity facility) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            session.merge(facility);
            tx.commit();
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
    }

    public boolean tryToReserve(ReservationsEntity reservation) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        boolean reserved = false;
        String test = "";
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            RoomsEntity rroom = reservation.getRoom();
            rroom = (RoomsEntity) session.get(RoomsEntity.class, rroom.getRoomPK());
            if(rroom==null) { //zle powiazanie miedzy systemami!
                throw new HibernateException("Error in linking between rooms!");
            }
            reservation.setRoom(rroom);  //update room!
            //no check for user because even if exist we will maybe update it..
            int count = session
               .createQuery("from ReservationsEntity WHERE room = :room AND NOT (departure <= :arrival OR arrival >= :departure)")
               .setParameter("room", rroom).setParameter("arrival", reservation.getArrival())
               .setParameter("departure", reservation.getDeparture()).list().size();
            test += "count:";
            if(count<rroom.getCount()) {  //we have enough rooms
                test += "saving..:";
                session.merge(reservation);
                test += "saved:";
                reserved = true;
            }
            tx.commit();
            test += "commited";
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(test);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
        return reserved;
    }

    public List<UsersEntity> getUsers() throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<UsersEntity> users;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            users = session.createQuery("from UsersEntity").list();
            tx.commit();
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
        return users;
    }

    public FacilitiesEntity getFacility(FacilitiesEntity facility) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            FacilitiesEntity f = (FacilitiesEntity) session.get(FacilitiesEntity.class, facility.getName());
            tx.commit();
            return f;
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
    }
}
