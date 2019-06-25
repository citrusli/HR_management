package control;

import entity.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SearchInfo {
    public List<Object[]> employee;
    public List<Object[]> checkHistory;
    public List<Object[]> department;
    public List<Object[]> post;
    public List<Object[]> record;
    public List<Object[]> salary;
    private static SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public boolean searchSelf(String userNo) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery queryEmploy = session.createSQLQuery("{CALL selectAllEmployee()}");
            SQLQuery queryCheck = session.createSQLQuery("{CALL selectAllCheck()}");
            SQLQuery queryDepart = session.createSQLQuery("{CALL selectAllDepart()}");
            SQLQuery queryPost = session.createSQLQuery("{CALL selectAllPost()}");
            SQLQuery queryRecord = session.createSQLQuery("{CALL selectAllRecord()}");
            SQLQuery querySalary = session.createSQLQuery("{CALL selectAllSalary()}");

            employee = queryEmploy.list();
            checkHistory = queryCheck.list();
            department = queryDepart.list();
            post = queryPost.list();
            record = queryRecord.list();
            salary = querySalary.list();

            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
           // session.close(); sessionFactory.close();
        }
        return true;
    }
}
