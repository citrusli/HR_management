package control;

import entity.Employee;
import entity.Post;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class logControl {
    String no;
    public boolean isManager;
    public boolean isHR;
    public boolean isUser;
    private static SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public boolean searchUser(String userNo,String userPassword) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery("{CALL selectAllEmployee()}");
            //query.setString(1,userNo);
            //query.setString(2,userPassword);
            List<Object[]> res = query.list();

            if(res == null || res.size() <= 0){       //未查询到该用户
                isUser = false;
                return false;
            }
                //isUser = true;
                for (Object[] obj : res) {
                    //取每一列
                   // System.out.println(obj[6] +" "+userPassword);
                    if(obj[6].toString().equals(userPassword) && obj[0].toString().equals(userNo)){
                        isUser = true;
                        break;
                    }
                }
                if(isUser == false)           //密码错误
                    return false;

            String departNo = userNo.substring(0,2);
            SQLQuery querydepart = session.createSQLQuery("{CALL selectALLDepart()}");
            List<Object[]> list = querydepart.list();
            if(list != null && list.size() >= 1){
                for (Object[] obj : list) {
                    //取每一列
                   // System.out.println(obj[0].toString() + " "+obj[1].toString() +obj[0].toString().equals(departNo) + " "+obj[1].toString().equals("人事部"));
                    if(obj[0].toString().equals(departNo) && obj[1].toString().equals("人事部")){
                        System.out.print("truer");
                        isHR = true;
                        break;
                    }
                }
            }

            String posttNo = userNo.substring(2,4);
            SQLQuery querypost = session.createSQLQuery("{CALL selectALLPost()}");
            List<Object[]> list2 = querypost.list();
            if(list2 != null && list2.size() >= 1){
                for (Object[] obj : list2) {
                    //取每一列
                    if(obj[0].toString().equals(posttNo) && obj[1].toString().equals("经理")){
                        isManager = true;
                        break;
                    }
                }
            }
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
           // session.close(); sessionFactory.close();
        }
        return true;
    }
}
