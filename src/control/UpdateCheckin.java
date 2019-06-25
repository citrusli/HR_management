package control;

import entity.Employee;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

public class UpdateCheckin {
    private static final SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void updateChin(String no) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("update chck set checkin = (?) WHERE employee_no = (?) AND check_d = (?)");
            query.setTime(1,new Date());
            query.setString(2,no);
            query.setDate(3,new Date());
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // session.close(); sessionFactory.close();
        }
    }
    public static void updateChout(String no) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery querys = session.createSQLQuery("select checkin from chck where employee_no = (?)");
            querys.setString(1,no);
            List list = querys.list();
            Date date = new Date();
           // System.out.print(list.toString());
            String status = "缺勤";
            if(Integer.parseInt(list.toString().substring(1,3)) > 8 && date.getHours() < 20)
                status = "迟到早退";
            else if(Integer.parseInt(list.toString().substring(1,3)) < 8 && date.getHours() > 20)
                status = "正常";
            else if(Integer.parseInt(list.toString().substring(1,3)) > 8 && date.getHours() > 20)
                status = "迟到";
            else if(Integer.parseInt(list.toString().substring(1,3)) < 8 && date.getHours() < 20)
                status = "早退";
            SQLQuery query2 = session.createSQLQuery("update chck set checkout = (?),check_status = (?) WHERE employee_no = (?) AND check_d = (?)");
            query2.setTime(1,date);
            query2.setString(2,status);
            query2.setString(3,no);
            query2.setDate(4,date);
            query2.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // session.close(); sessionFactory.close();
        }
    }
    public static void delcheck() {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            date.setDate(1);
            SQLQuery query = session.createSQLQuery("delete  from chck where check_d > (?)");
            query.setDate(1,date);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // session.close(); sessionFactory.close();
        }
    }
}
