package control;

import entity.Check;
import entity.Department;
import entity.Employee;
import entity.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Time;
import java.util.Date;

public class main {
    private static final SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void main(String[] args) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            //Employee student = new Employee("544456", "李四", "男", 32,"在职","15927162537","10232");
            //session.save(student);


            //java.sql.Date date = new java.sql.Date(new Date().getYear(),new Date().getMonth(),new Date().getDate());
           // Time time = new Time(new Date().getTime());
            //Check check  = new Check("123456",date,time,time,"正常");
            //session.save(check);
            //System.out.print(new Date().getDay());

            //Department depart = new Department("01","财务部","123456","三楼","01","内容");
            //session.save(depart);

            Post post = new Post("02","主管",200,"内容");
            session.save(post);

            //session.delete(student);
           /* SQLQuery query = session.createSQLQuery("{CALL findNameByID(?)}");
            query.setInteger(1,10001);
            List li = query.list();
            //query.setString(2,a);
            //query.executeUpdate();
            //li = query.list();
            System.out.print(li.get(0).toString());*/
            transaction.commit();


        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close(); sessionFactory.close();
        }
    }
}
