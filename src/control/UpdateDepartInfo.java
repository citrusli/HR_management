package control;

import entity.Department;
import entity.Employee;
import entity.Record;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UpdateDepartInfo {
    private static final SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void updateDepart(Department depart) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("UPDATE department SET " +
                    " department_name = (?),manager_no = (?), work_place = (?) " +
                    ", higher_department = (?),work_content = (?) WHERE department_no = (?)");
            query.setString(1,depart.getName());
            query.setString(2,depart.getManager_no());
            query.setString(3,depart.getPlace());
            query.setString(4,depart.getHigher());
            query.setString(5,depart.getWork_content());
            query.setString(6,depart.getNo());
            System.out.print(depart.getName() + " "+depart.getManager_no()+" "+depart.getHigher());
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
