package frame;

import com.sun.xml.fastinfoset.OctetBufferListener;
import control.Depart;
import control.Post;
import entity.Department;
import entity.Salary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class SalaryInfo extends JFrame{
    public java.util.List<Object[]> salary;
    public java.util.List<Object[]> employee;
    public static String no;
    JPanel mainPanel = new JPanel();
    Font font=new Font("宋体",Font.BOLD,18);
    JLabel label_name = new JLabel("姓名");
    JTextField text_name = new JTextField();
    JLabel label_salary = new JLabel("基本薪资");
    JTextField text_salary = new JTextField();
    JLabel label_postsalary = new JLabel("岗位薪资");
    JTextField area_postsalary = new JTextField();
    JLabel label_insurance = new JLabel("五险一金");
    JTextField area_insurance = new JTextField();
    JLabel label_allowance = new JLabel("津贴");
    JTextField area_allowance = new JTextField();
    JLabel label_pension = new JLabel("退休金");
    JTextField area_pension = new JTextField();
    JLabel label_define = new JLabel("说明");
    JTextArea area_define = new JTextArea();
    JButton button_save = new JButton("确定");
    JButton button_cancel = new JButton("取消");

    public SalaryInfo(String no,java.util.List<Object[]> salary,java.util.List<Object[]> employee){
        this.employee = employee;
        this.no = no;
        this.salary = salary;
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("薪资信息");
        this.mainPanel.setLayout(null);
        this.setBounds(400, 200, 500, 500);

        init();
        update();
        setfont(font);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setPanelBound();
            }
        });

        this.button_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });

        this.button_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text_salary.getText().replace(" ","").equals("") || area_pension.getText().replace(" ","").equals("")
                || area_allowance.getText().replace(" ","").equals("") || area_insurance.getText().replace(" ","").equals("")
                || area_postsalary.getText().replace(" ","").equals("")){
                    JOptionPane.showMessageDialog(null, "薪资不能为空", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    Double basic = Double.parseDouble(text_salary.getText());
                    Double post = Double.parseDouble(area_postsalary.getText());
                    Double insurance = Double.parseDouble(area_insurance.getText());
                    Double allowance = Double.parseDouble(area_allowance.getText());
                    Double pension = Double.parseDouble(area_pension.getText());
                    String define  = area_define.getText();
                    Salary sa = new Salary(no,define,basic,post,insurance,allowance,pension);
                }catch (Exception err){
                    JOptionPane.showMessageDialog(null, "薪资输入格式错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dispose();
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    void init(){
        mainPanel.add(label_name);
        mainPanel.add(text_name);
        text_name.setEnabled(false);
        mainPanel.add(label_postsalary);
        mainPanel.add(area_postsalary);
        area_postsalary.setEnabled(false);
        mainPanel.add(label_salary);
        mainPanel.add(text_salary);
        mainPanel.add(label_insurance);
        mainPanel.add(area_insurance);
        mainPanel.add(label_allowance);
        mainPanel.add(area_allowance);
        mainPanel.add(label_pension);
        mainPanel.add(area_pension);
        area_pension.setEnabled(false);
        mainPanel.add(label_define);
        mainPanel.add(area_define);
        mainPanel.add(button_cancel);
        mainPanel.add(button_save);
    }

    void setPanelBound(){
        Rectangle rec = mainPanel.getBounds();
        label_name.setBounds((int)(rec.width*0.1),(int)(rec.height*0.2),50,20);
        text_name.setBounds((int)(rec.width*0.2),(int)(rec.height*0.2),100,20);
        label_salary.setBounds((int)(rec.width*0.5),(int)(rec.height*0.2),50,20);
        text_salary.setBounds((int)(rec.width*0.6),(int)(rec.height*0.2),100,20);
        label_postsalary.setBounds((int)(rec.width*0.1),(int)(rec.height*0.4),50,20);
        area_postsalary.setBounds((int)(rec.width*0.2),(int)(rec.height*0.4),100,20);
        label_insurance.setBounds((int)(rec.width*0.5),(int)(rec.height*0.4),50,20);
        area_insurance.setBounds((int)(rec.width*0.6),(int)(rec.height*0.4),100,20);
        label_allowance.setBounds((int)(rec.width*0.1),(int)(rec.height*0.6),50,20);
        area_allowance.setBounds((int)(rec.width*0.2),(int)(rec.height*0.6),100,20);
        label_pension.setBounds((int)(rec.width*0.5),(int)(rec.height*0.6),50,20);
        area_pension.setBounds((int)(rec.width*0.6),(int)(rec.height*0.6),100,20);
        label_define.setBounds((int)(rec.width*0.1),(int)(rec.height*0.7),50,20);
        area_define.setBounds((int)(rec.width*0.2),(int)(rec.height*0.7),(int)(rec.width*0.6),100);
        button_save.setBounds((int)(rec.width*0.4),(int)(rec.height*0.9),80,20);
        button_cancel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.9),80,20);
    }

    void update(){
        if(salary != null && salary.size() > 0){
            for(Object[] obj : salary){
                if(obj[0].toString().equals(no)){
                    //this.text_name.setText(obj[1].toString());
                    this.text_salary.setText(obj[2].toString());
                    this.area_postsalary.setText(obj[3].toString());
                    this.area_insurance.setText(obj[4].toString());
                    this.area_allowance.setText(obj[5].toString());
                    this.area_pension.setText(obj[6].toString());
                    if(obj[1] != null) {
                        this.area_define.setText(obj[1].toString());
                    }
                    break;
                }
            }
        }
        if(employee != null && employee.size() > 0){
            for(Object[] obj : employee){
                if(obj[0].toString().equals(no)){
                    this.text_name.setText(obj[1].toString());
                    break;
                }
            }
        }
    }

    void setfont(Font f){
        label_allowance.setFont(f);
        area_allowance.setFont(f);
        label_define.setFont(f);
        area_define.setFont(f);
        label_insurance.setFont(f);
        area_insurance.setFont(f);
        label_name.setFont(f);
        text_name.setFont(f);
        label_pension.setFont(f);
        area_pension.setFont(f);
        label_postsalary.setFont(f);
        area_postsalary.setFont(f);
        label_salary.setFont(f);
        text_salary.setFont(f);
        button_cancel.setFont(f);
        button_save.setFont(f);
    }
}
