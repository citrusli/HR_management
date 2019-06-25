package control;

import entity.Employee;
import entity.Record;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UpdateSelfRecord {
    private static final SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void updateRecord(Record rec) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("UPDATE record SET " +
                    " birth_date = (?),nation = (?), birth_place = (?) " +
                    ", ID = (?),associate_degree = (?),enter_date = (?),address = (?), " +
                    "education = (?),notes = (?) WHERE employee_no = (?)");
            // query.setString(1,employee.getNo());
            query.setDate(1,rec.getBirth());
            query.setString(2,rec.getNation());
            query.setString(3,rec.getPlace());
            query.setString(4,rec.getID());
            query.setString(5,rec.getDegree());
            query.setDate(6,rec.getEnterDate());
            query.setString(7,rec.getAddress());
            query.setString(8,rec.getEducation());
            query.setString(9,rec.getNote());
            query.setString(10,rec.getNo());
           // System.out.println(query.toString());
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
