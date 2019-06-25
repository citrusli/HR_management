package entity;

import java.sql.Date;
import java.sql.Time;

public class Check implements java.io.Serializable{
    private String no;
    private Date date;
    private Time check_in;
    private Time check_out;
    private String status;

    public Check(){}

    public Check(String no,Date date,Time in,Time out,String status){
        this.no = no;
        this.date = date;
        this.check_in = in;
        this.check_out = out;
        this.status = status;
    }

    public String getNo(){
        return no;
    }
    public void setNo(String no){
        this.no = no;
    }

    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }

    public Time getCheck_in(){
        return check_in;
    }
    public void setCheck_in(Time in){
        this.check_in = in;
    }

    public Time getCheck_out(){
        return check_out;
    }
    public void setCheck_out(Time out){
        this.check_out = out;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }


    public int hasCode(){
        int result = 17;

        result = 37 * result + (getNo() == null ? 0 : this.getNo().hashCode());
        result = 37 * result
                + (getDate() == null ? 0 : this.getDate().hashCode());
        return result;
    }
}
