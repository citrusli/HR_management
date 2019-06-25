package control;

import entity.Employee;
import entity.Record;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UpdateSelfInfo {
    private static final SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void updateEmploy(Employee employee) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("UPDATE employee SET " +
                    " employee_name = (?),employee_gender = (?), employee_age = (?) " +
                    ", employee_status = (?) WHERE employee_no = (?)");
           // query.setString(1,employee.getNo());
            query.setString(1,employee.getName());
            query.setString(2,employee.getGender());
            query.setInteger(3,employee.getAge());
            query.setString(4,employee.getStatus());
            //query.setString(5,employee.getPassword());
            query.setString(5,employee.getNo());
            System.out.println(query.toString());
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
           // session.close(); sessionFactory.close();
        }
        }
    public static void updateEmployRec(Employee employee, Record record) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("UPDATE employee SET " +
                    " employee_name = (?),employee_gender = (?), employee_age = (?) " +
                    ", employee_status = (?) WHERE employee_no = (?)");
            // query.setString(1,employee.getNo());
            query.setString(1,employee.getName());
            query.setString(2,employee.getGender());
            query.setInteger(3,employee.getAge());
            query.setString(4,employee.getStatus());
            query.setString(5,employee.getNo());
            System.out.println(query.toString());
            query.executeUpdate();

            SQLQuery queryrec = session.createSQLQuery("UPDATE record SET " +
                    " birth_date = (?),nation = (?), birth_place = (?) " +
                    ", ID = (?),associate_degree = (?),enter_date = (?),address = (?), " +
                    "education = (?),notes = (?) WHERE employee_no = (?)");
            // query.setString(1,employee.getNo());
            queryrec.setDate(1,record.getBirth());
            queryrec.setString(2,record.getNation());
            queryrec.setString(3,record.getPlace());
            queryrec.setString(4,record.getID());
            queryrec.setString(5,record.getDegree());
            queryrec.setDate(6,record.getEnterDate());
            queryrec.setString(7,record.getAddress());
            queryrec.setString(8,record.getEducation());
            queryrec.setString(9,record.getNote());
            queryrec.setString(10,record.getNo());
            // System.out.println(query.toString());
            queryrec.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // session.close(); sessionFactory.close();
        }
    }
}
