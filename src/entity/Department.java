package entity;

public class Department {
    private String no;
    private String name;
    private String manager_no;
    private String place;
    private String higher;
    private String work_content;

    public Department(){}

    public Department(String no,String name,String manager_no,String place,String higher,String work_content){
        this.no = no;
        this.name = name;
        this.manager_no = manager_no;
        this.place = place;
        this.higher = higher;
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

    public String getManager_no(){
        return manager_no;
    }
    public void setManager_no(String manager_no){
        this.manager_no = manager_no;
    }

    public String getPlace(){
        return place;
    }
    public void setPlace(String place){
        this.place = place;
    }

    public String getHigher(){
        return higher;
    }
    public void setHigher(String higher){
        this.higher = higher;
    }

    public String getWork_content(){
        return work_content;
    }
    public void setWork_content(String work_content){
        this.work_content = work_content;
    }
}
