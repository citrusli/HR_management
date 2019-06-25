package control;

import entity.Check;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

public class InsertCheck {
    private static final SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void insertCheck(String no) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Check check = new Check(no, new Date(System.currentTimeMillis()), null, null,"缺勤");
            session.save(check);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
           // session.close(); sessionFactory.close();
        }
    }
}
