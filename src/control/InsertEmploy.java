package control;

import entity.Employee;
import entity.Record;
import entity.Salary;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InsertEmploy {
    private static final SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void insertEm(Employee employee, Record record, Salary salary) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("insert into employee values ((?),(?),(?),(?),(?),(?),(?))");
            // query.setString(1,employee.getNo());
            query.setString(1,employee.getNo());
            query.setString(2,employee.getName());
            query.setString(3,employee.getGender());
            query.setInteger(4,employee.getAge());
            query.setString(5,employee.getStatus());
            query.setString(6,employee.getTel());
            query.setString(7,employee.getPassword());
            //query.executeUpdate();
            session.save(employee);
            session.save(record);
            session.save(salary);

            SQLQuery insertRec = session.createSQLQuery("insert into record values ((?),(?),(?),(?),(?),(?),(?),(?),(?),(?))");
            insertRec.setString(1,record.getNo());
            insertRec.setDate(2,record.getBirth());
            insertRec.setString(3,record.getNation());
            insertRec.setString(4,record.getPlace());
            insertRec.setString(5,record.getID());
            insertRec.setString(6,record.getDegree());
            insertRec.setDate(7,record.getEnterDate());
            insertRec.setString(8,record.getAddress());
            insertRec.setString(9,record.getEducation());
            insertRec.setString(10,record.getNote());
           // insertRec.executeUpdate();

            SQLQuery insertSa = session.createSQLQuery("insert into salary values ((?),(?),(?),(?),(?),(?),(?))");
            insertSa.setString(1,salary.getNo());
            insertSa.setString(2,salary.getDefine());
            insertSa.setDouble(3,salary.getBasic());
            insertSa.setDouble(4,salary.getPost());
            insertSa.setDouble(5,salary.getInsurance());
            insertSa.setDouble(6,salary.getAllowance());
            insertSa.setDouble(7,salary.getPension());
           // insertSa.executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // session.close(); sessionFactory.close();
        }
    }
}
