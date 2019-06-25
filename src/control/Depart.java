package control;

import entity.Department;
import entity.Employee;
import entity.Record;
import entity.Salary;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Depart {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void addDepart(String emno, String oldno, Department depart) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("UPDATE employee SET " +
                    " employee_no = (?) WHERE employee_no = (?)");
            query.setString(1, emno);
            query.setString(2, oldno);
            query.executeUpdate();

           /* SQLQuery queryde = session.createSQLQuery("insert into department values ((?),(?),(?),(?),(?),(?))");
            queryde.setString(1,emno);
            queryde.setString(2,oldno);
            queryde.setString(1,emno);
            queryde.setString(2,oldno);
            queryde.setString(1,emno);
            queryde.setString(2,oldno);*/

            session.save(depart);

            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // session.close(); sessionFactory.close();
        }
    }

    public static void updateDepart(Department depart) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("UPDATE department SET " +
                    " department_name = (?),manager_no = (?), work_place = (?) " +
                    ", higher_department = (?),work_content = (?) WHERE department_no = (?)");
            // query.setString(1,employee.getNo());
            query.setString(1, depart.getName());
            query.setString(2, depart.getManager_no());
            query.setString(3, depart.getPlace());
            query.setString(4, depart.getHigher());
            query.setString(5, depart.getWork_content());
            query.setString(6, depart.getNo());
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // session.close(); sessionFactory.close();
        }
    }

    public static void delDepart(String[] no) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery dele = session.createSQLQuery("delete from department where department_no=(?)");
            for (int i = 0; i < no.length; i++) {
                dele.setString(1, no[i]);
                dele.executeUpdate();
                transaction.commit();

            }
        }catch(Exception e){
                transaction.rollback();
                e.printStackTrace();
            } finally{
                // session.close(); sessionFactory.close();
            }
        }
}
