package entity;

import javax.xml.crypto.Data;
import java.sql.Date;

public class Record {
    private String no;
    private Date birth;
    private String nation;
    private String place;
    private String ID;
    private String degree;
    private Date enterDate;
    private String address;
    private String education;
    private String note;

    public Record(){}

    public Record(String no,Date birth,String nation,String place,String ID,String degree,Date enter,String address,String education,String note){
        this.no = no;
        this.birth = birth;
        this.nation = nation;
        this.place = place;
        this.ID = ID;
        this.degree = degree;
        this.enterDate = enter;
        this.address = address;
        this.education = education;
        this.note = note;
    }

    public String getNo(){
        return no;
    }
    public void setNo(String no){
        this.no = no;
    }

    public Date getBirth(){
        return birth;
    }
    public void setBirth(Date birth){
        this.birth = birth;
    }

    public String getNation(){
        return nation;
    }
    public void setNation(String nation){
        this.nation =nation;
    }

    public String getPlace(){
        return place;
    }
    public void setPlace(String place){
        this.place = place;
    }

    public String getID(){
        return ID;
    }
    public void setID(String ID){
        this.ID = ID;
    }

    public String getDegree(){
        return degree;
    }
    public void setDegree(String degree){
        this.degree = degree;
    }

    public Date getEnterDate(){
        return enterDate;
    }
    public void setEnterDate(Date enter){
        this.enterDate = enter;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getEducation(){
        return education;
    }
    public void setEducation(String education){
        this.education = education;
    }

    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }

}
