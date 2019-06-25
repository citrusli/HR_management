package entity;

public class Employee {
    private String no;
    private String name;
    private String gender;
    private int age;
    private String status;
    private String tel;
    private String password;

    public Employee(){}

    public Employee(String no, String name, String gender, int age, String status, String tel,String password){
        this.no = no;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.status = status;
        this.tel = tel;
        this.password = password;
    }

    public String getNo(){
        return no;
    }
    public void setNo(String no){
        this.no = no;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String getTel(){
        return tel;
    }
    public void setTel(String tel){
        this.tel = tel;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
