package control;

import entity.Salary;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UpdateSalary {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void updatesalary(Salary salary){
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("UPDATE salary SET " +
                    " define = (?),basic_salary = (?), insurance = (?) " +
                    ", allowance = (?) WHERE employee_no = (?)");
            // query.setString(1,employee.getNo());
            query.setString(1, salary.getDefine());
            query.setDouble(2, salary.getBasic());
            query.setDouble(3, salary.getInsurance());
            query.setDouble(4, salary.getAllowance());
            query.setString(5, salary.getNo());
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
