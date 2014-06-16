package pl.bb.broker.company.companydb.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 01:46
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil implements IHibernateUtil {
    private static HibernateUtil instance;

    public static HibernateUtil getInstance() {
        if(instance==null) {
            instance = new HibernateUtil();
        }
        return instance;
    }

    private final SessionFactory SESSION_FACTORY;


    {
        try {
            Configuration conf = new Configuration();
            conf.configure("hibernate-company.cfg.xml");
            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(conf.getProperties());
            StandardServiceRegistry registry = sb.build();
            SESSION_FACTORY = conf.buildSessionFactory(registry);
        } catch (Throwable th) {
            throw new ExceptionInInitializerError(th);
        }
    }

    public Session getSession() throws HibernateException {
        return SESSION_FACTORY.openSession();
    }

}
