package pl.bb.broker.company.companydb.util;

import org.hibernate.HibernateException;
import pl.bb.broker.company.companydb.pensjonat.entities.FacilitiesEntity;
import pl.bb.broker.company.companydb.pensjonat.entities.ReservationsEntity;
import pl.bb.broker.company.companydb.pensjonat.entities.UsersEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 29.05.14
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
public interface PensjonatDBReservationsUtil {
    PensjonatDBReservationsUtil FACTORY = new PensjonatDBUtil();

    boolean tryToReserve(ReservationsEntity reservation) throws HibernateException;

    public List<UsersEntity> getUsers() throws HibernateException;
}
