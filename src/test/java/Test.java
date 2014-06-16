import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.bb.broker.company.companydb.pensjonat.entities.ReservationsEntity;
import pl.bb.broker.company.companydb.pensjonat.entities.RoomsEntity;
import pl.bb.broker.company.companydb.util.HibernateConfiguration;
import pl.bb.broker.company.companydb.util.IHibernateUtil;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 02.06.14
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    @org.junit.Test
    public void testdb() {
        IHibernateUtil hibernateUtil = HibernateConfiguration.getSessionFactory();
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            Object o = session.get(ReservationsEntity.class, 1);
            tx.commit();
            assert o==null : "not null";
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
