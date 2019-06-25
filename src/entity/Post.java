package entity;

public class Post {
    private String no;
    private String name;
    private double salary;
    private String work_content;

    public Post(){}

    public Post(String no,String name,double salary,String work_content){
        this.no = no;
        this.name = name;
        this.salary = salary;
        this.work_content = work_content;
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

    public double getSalary(){
        return salary;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }

    public String getWork_content(){
        return work_content;
    }
    public void setWork_content(String work_content){
        this.work_content = work_content;
    }
}
