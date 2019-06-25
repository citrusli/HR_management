package entity;

public class Salary {
    private String no;
    private String define;
    private double basic;
    private double post;
    private double insurance;
    private double allowance;
    private double pension;

    public Salary(){}

    public Salary(String no,String define,double basic,double post,double insurance,double allowance,double pension){
        this.no = no;
        this.define = define;
        this.basic = basic;
        this.post = post;
        this.insurance = insurance;
        this.allowance = allowance;
        this.pension = pension;
    }

    public String getNo(){
        return no;
    }
    public void setNo(String no){
        this.no = no;
    }

    public String getDefine(){
        return define;
    }
    public void setDefine(String define){
        this.define = define;
    }

    public double getBasic(){
        return basic;
    }
    public void setBasic(double basic){
        this.basic = basic;
    }

    public double getPost(){
        return post;
    }
    public void setPost(double post){
        this.post = post;
    }

    public double getInsurance(){
        return insurance;
    }
    public void setInsurance(double insurance){
        this.insurance = insurance;
    }

    public double getAllowance(){
        return allowance;
    }
    public void setAllowance(double allowance){
        this.allowance = allowance;
    }

    public double getPension(){
        return pension;
    }
    public void setPension(double pension){
        this.pension = pension;
    }
}
