package frame;

import control.InsertEmploy;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddEmploy extends JFrame {
    public java.util.List<Object[]> employee;
    public java.util.List<Object[]> checkHistory;
    public java.util.List<Object[]> department;
    public java.util.List<Object[]> post;
    public java.util.List<Object[]> record;
    public List<Object[]> salary;
    JPanel mainPanel = new JPanel();
    Font font=new Font("宋体",Font.BOLD,18);
    JLabel label_depart = new JLabel("部门");
    JComboBox<String> combo_depart = new JComboBox<String>();
    JLabel label_post = new JLabel("岗位");
    JComboBox<String> combo_post = new JComboBox<String>();
    JLabel label_selfNo = new JLabel("编号");
    JTextField text_selfNo = new JTextField();
    JLabel label_selfName = new JLabel("姓名");
    JTextField text_selfName = new JTextField();
    JLabel label_selfGender = new JLabel("性别");
    String[] genderData = new String[]{"男","女"};
    JComboBox<String> combo_selfGender = new JComboBox<String>(genderData);
    JLabel label_selfAge = new JLabel("年龄");
    JTextField text_selfAge = new JTextField();
    JTextField text_selfstatus = new JTextField();
    JLabel label_selfTel = new JLabel("联系方式");
    JTextField text_selfTel = new JTextField();
    JButton button_selfInfoSave = new JButton("保存");
    JButton button_selfInfoCancel = new JButton("取消");
    JLabel label_recordBirthDate = new JLabel("出生日期");
    JTextField text_recordBirthDate = new JTextField();
    JLabel label_birthDateMessage = new JLabel("日期格式YY-MM-DD");
    JLabel label_recordNation = new JLabel("民族");
    String[] nationData = new String[]{"汉族", "蒙古族", "回族", "维吾尔族"};
    JComboBox<String> combo_recordNation = new JComboBox<String>(nationData);
    JLabel label_recordBirthPlace = new JLabel("籍贯");
    String[] birthPlaceData = new String[]{"北京", "河北", "山西", "辽宁"};
    JComboBox<String> combo_recordBirthPlace = new JComboBox<String>(birthPlaceData);
    JLabel label_recordID = new JLabel("身份证号");
    JTextField text_recordID = new JTextField();
    JLabel label_recordDegree = new JLabel("学历");
    JTextField text_recordDegree = new JTextField();
    JLabel label_recordEnterDate = new JLabel("入职时间");
    JTextField text_recordEnterDate = new JTextField();
    JLabel label_EnterDateMessage = new JLabel("日期格式YY-MM-DD");
    JLabel label_recordAddress = new JLabel("住址");
    JTextArea area_recordAddress = new JTextArea();
    JLabel label_recordEdu = new JLabel("教育经历");
    JTextArea area_recordEdu = new JTextArea();
    JLabel label_recordNote = new JLabel("备注");
    JTextArea area_recordNote = new JTextArea();
    public AddEmploy(List<Object[]> em, List<Object[]> ch, List<Object[]> de, List<Object[]> po, List<Object[]> re,List<Object[]> sa) {
        this.employee = em;
        this.department = de;
        this.post = po;
        this.salary = sa;
        this.checkHistory = ch;
        this.record = re;
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("添加职工");
        this.mainPanel.setLayout(null);
        this.setBounds(400, 200, 700, 600);

        this.setContentPane(mainPanel);

        initPanel();
        setPanelBound();
        update();
        setfont(font);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setPanelBound();
            }
        });

        this.button_selfInfoCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });

        this.button_selfInfoSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //添加职工
                try {
                    Exception exp = new Exception();
                    String name = text_selfName.getText();
                    String gender = combo_selfGender.getSelectedItem().toString();
                    int age = Integer.parseInt(text_selfAge.getText());
                    String tel = text_selfTel.getText();
                    String id = text_recordID.getText();
                    String birth = text_recordBirthDate.getText();
                    String nation = combo_recordNation.getSelectedItem().toString();
                    String place = combo_recordBirthPlace.getSelectedItem().toString();
                    String degree = text_recordDegree.getText();
                    String enter = text_recordEnterDate.getText();
                    //System.out.println("接受所有数据");
                    int basic = 0;
                    if(age <= 18 || age >= 80) {
                        JOptionPane.showMessageDialog(null, "年龄不合法", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //System.out.println("年龄验证成功");
                    if(tel.length() != 11) {
                        JOptionPane.showMessageDialog(null, "联系方式长度错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    for(char ch : tel.toCharArray()){
                        if(ch < '0' || ch > '9')
                        {
                            JOptionPane.showMessageDialog(null, "联系方式字符错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                   // System.out.println("联系方式验证成功");
                    if(id.length() != 18) {
                        JOptionPane.showMessageDialog(null, "身份证号长度错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    for(char ch : id.toCharArray()){
                        if(!((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')))
                        {
                            JOptionPane.showMessageDialog(null, "身份证字符错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    //System.out.println("身份证号验证成功");
                    if(!isIlegal(birth) || !isIlegal(enter))
                    {
                        JOptionPane.showMessageDialog(null, "时间格式错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    Date birthdate = new Date(simpleDateFormat.parse( birth ).getTime());
                    Date enterdate = new Date(simpleDateFormat.parse( enter ).getTime());
                   // System.out.println("时间验证成功");
                    String departno = "";
                    String postno = "";
                    if(department != null && department.size() > 0){
                        for(Object[] obj : department){
                            if(obj[1].toString().equals(combo_depart.getSelectedItem().toString())) {
                               // System.out.println(departno);
                                departno = (obj[0].toString());
                               // System.out.println(departno);
                               // System.out.println("部门"+obj[0].toString());
                            }
                        }
                    }
                    if(post != null && post.size() > 0){
                        for(Object[] obj : post){
                            if(obj[1].toString().equals(combo_post.getSelectedItem().toString())) {
                               // System.out.println("职位"+obj[0].toString());
                                postno = departno +(obj[0].toString());
                               basic = (int)Double.parseDouble(obj[2].toString());
                                //System.out.println(obj[2].toString());
                            }
                        }
                    }
                    //System.out.println(postno);
                    int k = 1;
                    boolean isExist = false;
                    do{
                        isExist = false;
                    if(employee != null && employee.size() > 0){
                        for(Object[] obj : employee){
                            if(obj[0].toString().substring(0,4).equals(postno) && Integer.parseInt(obj[0].toString().substring(4)) == k){
                                isExist = true;
                                break;
                            }
                            if(obj[0].toString().substring(0,4).equals(postno) && combo_post.getSelectedItem().equals("经理")){
                                JOptionPane.showMessageDialog(null, "该部门已存在经理", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        //System.out.println(postno+k);
                    }}while (isExist && k++ < 100);
                    k--;
                    //System.out.print(k);
                    String no ="";
                    String res = "";
                    if( k == 0){
                        no = postno+"00";
                    }
                    else if(k < 10) {
                        k *= 10;
                        res = postno + "0" ;
                        //res = ""+k;
                        no = res+(""+k+"");
                    }
                    else {
                        //res = postno + ("" + k);
                        no = res + "" + k;
                    }
                    Employee newEmployee = new Employee(no,name,gender,age,"在职",tel,"000000");
                    Record newRecord = new Record(no,birthdate,nation,place,id,degree,enterdate,area_recordAddress.getText(),area_recordEdu.getText(),area_recordNote.getText());
                    Salary newSalary = new Salary(no,"",2000,basic,200,200,0);
                    InsertEmploy.insertEm(newEmployee,newRecord,newSalary);
                }catch (Exception err){
                    System.out.print(err.getCause());
                    JOptionPane.showMessageDialog(null, "输入数据格式错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dispose();
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    StringBuffer getNo(StringBuffer str,int k){
        if(k >= 0 && k <10){
            return str.append("0"+k);
        }
        else if(k >= 10 && k <100){
            return str.append(""+k);
        }
        else
            return null;
    }

    boolean isIlegal(String date){
        String[] data = date.split("-");
        if(data.length == 3){
            try {
                int year = Integer.parseInt(data[0]);
                int month = Integer.parseInt(data[1]);
                int day = Integer.parseInt(data[2]);
                if(year <= 0 || year > 2019 || month <= 0 || month > 12|| day < 1 || day >31)
                    return false;
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    //初始化控件
    void initPanel(){
        mainPanel.add(label_selfNo);
        mainPanel.add(combo_post);
        combo_post.setFont(font);
        mainPanel.add(combo_depart);
        combo_depart.setFont(font);
        mainPanel.add(label_post);
        label_post.setFont(font);
        mainPanel.add(label_depart);
        label_depart.setFont(font);
        label_selfNo.setFont(font);
        mainPanel.add(text_selfNo);
        text_selfNo.setEnabled(false);
        text_selfNo.setFont(font);
        mainPanel.add(label_selfName);
        label_selfName.setFont(font);
        mainPanel.add(text_selfName);
        text_selfName.setFont(font);
        mainPanel.add(label_selfGender);
        label_selfGender.setFont(font);
        mainPanel.add(combo_selfGender);
        combo_selfGender.setFont(font);
        mainPanel.add(label_selfAge);
        label_selfAge.setFont(font);
        mainPanel.add(text_selfAge);
        text_selfAge.setFont(font);
        mainPanel.add(label_selfTel);
        label_selfTel.setFont(font);
        mainPanel.add(text_selfTel);
        text_selfTel.setFont(font);
        mainPanel.add(label_recordBirthDate);
        label_recordBirthDate.setFont(font);
        mainPanel.add(text_recordBirthDate);
        text_recordBirthDate.setFont(font);
        mainPanel.add(label_birthDateMessage);
        label_birthDateMessage.setFont(font);
        mainPanel.add(label_recordNation);
        label_recordNation.setFont(font);
        mainPanel.add(combo_recordNation);
        combo_recordNation.setFont(font);
        mainPanel.add(label_recordBirthPlace);
        label_recordBirthPlace.setFont(font);
        mainPanel.add(combo_recordBirthPlace);
        combo_recordBirthPlace.setFont(font);
        mainPanel.add(label_recordID);
        label_recordID.setFont(font);
        mainPanel.add(text_recordID);
        text_recordID.setFont(font);
        mainPanel.add(label_recordDegree);
        label_recordDegree.setFont(font);
        mainPanel.add(text_recordDegree);
        text_recordDegree.setFont(font);
        mainPanel.add(label_recordEnterDate);
        label_recordEnterDate.setFont(font);
        mainPanel.add(text_recordEnterDate);
        text_recordEnterDate.setFont(font);
        mainPanel.add(label_EnterDateMessage);
        label_EnterDateMessage.setFont(font);
        mainPanel.add(label_recordAddress);
        label_recordAddress.setFont(font);
        mainPanel.add(area_recordAddress);
        area_recordAddress.setFont(font);
        mainPanel.add(label_recordEdu);
        label_recordEdu.setFont(font);
        mainPanel.add(area_recordEdu);
        area_recordEdu.setFont(font);
        mainPanel.add(label_recordNote);
        label_recordNote.setFont(font);
        mainPanel.add(area_recordNote);
        area_recordNote.setFont(font);
        mainPanel.add(button_selfInfoCancel);
        button_selfInfoCancel.setFont(font);
        mainPanel.add(button_selfInfoSave);
        button_selfInfoSave.setFont(font);
    }

    //设置控件位置
    void setPanelBound(){
        Rectangle rec = this.getBounds();
        label_selfName.setBounds((int)(rec.width*0.1),(int)(rec.height*0.1),50,20);
        text_selfName.setBounds((int)(rec.width*0.2),(int)(rec.height*0.1),100,20);
        label_recordDegree.setBounds((int)(rec.width*0.5),(int)(rec.height*0.1),50,20);
        text_recordDegree.setBounds((int)(rec.width*0.6),(int)(rec.height*0.1),100,20);
        label_selfGender.setBounds((int)(rec.width*0.1),(int)(rec.height*0.2),50,20);
        combo_selfGender.setBounds((int)(rec.width*0.2),(int)(rec.height*0.2),100,20);
        label_selfAge.setBounds((int)(rec.width*0.5),(int)(rec.height*0.2),50,20);
        text_selfAge.setBounds((int)(rec.width*0.6),(int)(rec.height*0.2),100,20);
        label_selfTel.setBounds((int)(rec.width*0.1),(int)(rec.height*0.3),50,20);
        text_selfTel.setBounds((int)(rec.width*0.2),(int)(rec.height*0.3),100,20);
        label_recordID.setBounds((int)(rec.width*0.5),(int)(rec.height*0.3),50,20);
        text_recordID.setBounds((int)(rec.width*0.6),(int)(rec.height*0.3),150,20);
        label_recordBirthDate.setBounds((int)(rec.width*0.1),(int)(rec.height*0.4),50,20);
        text_recordBirthDate.setBounds((int)(rec.width*0.2),(int)(rec.height*0.4),100,20);
        label_birthDateMessage.setBounds((int)(rec.width*0.5),(int)(rec.height*0.4),200,20);
        label_depart.setBounds((int)(rec.width*0.1),(int)(rec.height*0.5),50,20);
        combo_depart.setBounds((int)(rec.width*0.2),(int)(rec.height*0.5),100,20);
        label_post.setBounds((int)(rec.width*0.5),(int)(rec.height*0.5),50,20);
        combo_post.setBounds((int)(rec.width*0.6),(int)(rec.height*0.5),100,20);
        label_recordNation.setBounds((int)(rec.width*0.1),(int)(rec.height*0.6),50,20);
        combo_recordNation.setBounds((int)(rec.width*0.2),(int)(rec.height*0.6),100,20);
        label_recordBirthPlace.setBounds((int)(rec.width*0.5),(int)(rec.height*0.6),50,20);
        combo_recordBirthPlace.setBounds((int)(rec.width*0.6),(int)(rec.height*0.6),100,20);
        label_recordEnterDate.setBounds((int)(rec.width*0.1),(int)(rec.height*0.7),50,20);
        text_recordEnterDate.setBounds((int)(rec.width*0.2),(int)(rec.height*0.7),100,20);
        label_EnterDateMessage.setBounds((int)(rec.width*0.5),(int)(rec.height*0.7),200,20);
        button_selfInfoSave.setBounds((int)(rec.width*0.4),(int)(rec.height*0.9),80,20);
        button_selfInfoCancel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.9),80,20);
    }

    void update(){
        text_selfName.setText("");
        combo_selfGender.setSelectedItem("男");
        text_selfAge.setText("");
        text_selfTel.setText("");
        text_recordID.setText("");
        text_recordBirthDate.setText("");
        combo_recordNation.setSelectedItem("汉族");
        combo_recordBirthPlace.setSelectedItem("北京");
        text_recordDegree.setText("");
        text_recordEnterDate.setText("");
        combo_depart.removeAllItems();
        if (department != null && department.size() > 0){
            for (Object[] obj : department){
                combo_depart.addItem(obj[1].toString());
            }
        }
        combo_post.removeAllItems();
        if (post != null && post.size() > 0){
            for (Object[] obj : post){
                combo_post.addItem(obj[1].toString());
            }
        }
    }

    void setfont(Font f){
        label_recordDegree.setFont(f);
        text_recordDegree.setFont(f);
        label_recordEnterDate.setFont(f);
        text_recordEnterDate.setFont(f);
        label_recordBirthPlace.setFont(f);
        combo_recordBirthPlace.setFont(f);
        label_recordNation.setFont(f);
        combo_recordNation.setFont(f);
        label_recordBirthDate.setFont(f);
        text_recordBirthDate.setFont(f);
        label_recordID.setFont(f);
        text_recordID.setFont(f);
        label_recordNote.setFont(f);
        area_recordNote.setFont(f);
        label_recordEdu.setFont(f);
        area_recordEdu.setFont(f);
        label_recordAddress.setFont(f);
        area_recordAddress.setFont(f);
        label_selfName.setFont(f);
        text_selfName.setFont(f);
        label_post.setFont(f);
        combo_post.setFont(f);
        label_depart.setFont(f);
        combo_depart.setFont(f);
        label_EnterDateMessage.setFont(f);
        label_birthDateMessage.setFont(f);
        label_selfTel.setFont(f);
        text_selfTel.setFont(f);
        label_selfAge.setFont(f);
        text_selfAge.setFont(f);
        label_selfNo.setFont(f);
        text_selfNo.setFont(f);
        label_selfGender.setFont(f);
        combo_selfGender.setFont(f);
        button_selfInfoSave.setFont(f);
        button_selfInfoCancel.setFont(f);
    }
}
