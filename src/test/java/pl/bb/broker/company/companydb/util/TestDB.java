package pl.bb.broker.company.companydb.util;

import org.junit.Test;
import pl.bb.broker.company.companydb.pensjonat.entities.FacilitiesEntity;
import pl.bb.broker.company.companydb.pensjonat.entities.ReservationsEntity;
import pl.bb.broker.company.companydb.pensjonat.entities.RoomsEntity;
import pl.bb.broker.company.companydb.pensjonat.entities.UsersEntity;

import java.lang.reflect.Field;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 12.06.14
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class TestDB {

    @Test
    public void testReserv() throws Exception {
        Field field = HibernateConfiguration.class.getDeclaredField("sessionFactory");
        field.setAccessible(true);
        field.set(null, TestHibernateUtil.getInstance());

        UsersEntity user = new UsersEntity();
        user.setUsername("luffy");
        user.setFirstname("luffy");
        user.setSurname("Monkey D.");

        FacilitiesEntity facility = new FacilitiesEntity();
        facility.setName("scout camp");

        RoomsEntity room = new RoomsEntity();
        room.setRtype("N10");
        room.setFacility(facility);

        ReservationsEntity reservation = new ReservationsEntity();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setArrival(new Date(0L));
        reservation.setDeparture(new Date(1000000000000L));

        PensjonatDBReservationsUtil.FACTORY.tryToReserve(reservation);
    }
}
