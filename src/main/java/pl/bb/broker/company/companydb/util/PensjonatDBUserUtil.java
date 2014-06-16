package pl.bb.broker.company.companydb.util;

import org.hibernate.HibernateException;
import pl.bb.broker.company.companydb.pensjonat.entities.FacilitiesEntity;
import pl.bb.broker.company.companydb.pensjonat.entities.InvoicesEntity;
import pl.bb.broker.company.companydb.pensjonat.entities.RoomsEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 14.06.14
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 */
public interface PensjonatDBUserUtil {
    PensjonatDBUserUtil FACTORY = new PensjonatDBUtil();

    FacilitiesEntity getFacility(FacilitiesEntity facility) throws HibernateException;

    void saveRooms(List<RoomsEntity> rooms) throws HibernateException;
    void saveUpdateFacility(FacilitiesEntity facility) throws HibernateException;
}
