package control;

import entity.Check;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

public class DelEmploy {
    private static final SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void del(String[] no) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery dele = session.createSQLQuery("delete from employee where employee_no=(?)");
            for(int i = 0; i < no.length;i ++){
                dele.setString(1,no[i]);
                dele.executeUpdate();
            }
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // session.close(); sessionFactory.close();
        }
    }
}
