package pl.bb.broker.company.companydb.util;

import org.hibernate.HibernateException;
import pl.bb.broker.company.companydb.pensjonat.entities.InvoicesEntity;
import pl.bb.broker.company.companydb.pensjonat.entities.ReservationsEntity;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 29.05.14
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
public interface PensjonatDBInvoicesUtil {
    PensjonatDBInvoicesUtil FACTORY = new PensjonatDBUtil();

    void saveInvoice(InvoicesEntity invoice) throws HibernateException;
}
