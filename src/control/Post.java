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

public class Post {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void addPost(entity.Post post) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {

            session.save(post);

            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // session.close(); sessionFactory.close();
        }
    }

    public static void updatePost(entity.Post post) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("UPDATE post SET " +
                    " post_name = (?),post_salary = (?), work_content = (?)  WHERE post_no = (?)");
            // query.setString(1,employee.getNo());
            query.setString(1, post.getName());
            query.setDouble(2, post.getSalary());
            query.setString(3, post.getWork_content());
            query.setString(4, post.getNo());
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // session.close(); sessionFactory.close();
        }
    }

    public static void delPost(String[] no) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery dele = session.createSQLQuery("delete from post where post_no=(?)");
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
