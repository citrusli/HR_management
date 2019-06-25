package frame;

import com.sun.xml.fastinfoset.OctetBufferListener;
import control.Depart;
import control.Post;
import entity.Department;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class PostInfo extends JFrame{
    public java.util.List<Object[]> post;
    public static String no;
    public static String name;
    public static Double salary;
    JPanel mainPanel = new JPanel();
    Font font=new Font("宋体",Font.BOLD,18);
    JLabel label_name = new JLabel("名称");
    JTextField text_name = new JTextField();
    JLabel label_salary = new JLabel("岗位薪资");
    JTextField text_salary = new JTextField();
    JLabel label_workcontent = new JLabel("工作内容");
    JTextArea area_workcontent = new JTextArea();
    JButton button_save = new JButton("确定");
    JButton button_cancel = new JButton("取消");

    public PostInfo(String no,java.util.List<Object[]> po){
        this.no = no;
        this.post = po;
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("岗位信息");
        this.mainPanel.setLayout(null);
        this.setBounds(400, 200, 500, 400);

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
                String name = text_name.getText();
                String salary = text_salary.getText();
                String content = area_workcontent.getText();
                if(name.replace(" ","") == "" || salary.replace(" ","") == ""){
                    JOptionPane.showMessageDialog(null, "名称或薪资不能为空", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //验证名称是否重复
                if(post != null && post.size() > 0){
                    for(Object[] obj : post){
                        if(obj[1].toString().equals(name) && !obj[1].toString().equals(PostInfo.name)){
                            JOptionPane.showMessageDialog(null, "该岗位已存在", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                try {
                    PostInfo.salary = Double.parseDouble(salary);
                }catch (Exception err){
                    JOptionPane.showMessageDialog(null, "薪资输入格式错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                entity.Post post1 = new entity.Post(no,name,PostInfo.salary,content);
                Post.updatePost(post1);
                dispose();
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    void init(){
        mainPanel.add(label_name);
        mainPanel.add(text_name);
        mainPanel.add(label_workcontent);
        mainPanel.add(area_workcontent);
        mainPanel.add(label_salary);
        mainPanel.add(text_salary);
        mainPanel.add(button_cancel);
        mainPanel.add(button_save);
    }

    void setPanelBound(){
        Rectangle rec = mainPanel.getBounds();
        label_name.setBounds((int)(rec.width*0.1),(int)(rec.height*0.3),50,20);
        text_name.setBounds((int)(rec.width*0.2),(int)(rec.height*0.3),100,20);
        label_salary.setBounds((int)(rec.width*0.5),(int)(rec.height*0.3),50,20);
        text_salary.setBounds((int)(rec.width*0.6),(int)(rec.height*0.3),100,20);
        label_workcontent.setBounds((int)(rec.width*0.1),(int)(rec.height*0.6),50,20);
        area_workcontent.setBounds((int)(rec.width*0.2),(int)(rec.height*0.6),(int)(rec.width*0.6),100);
        button_save.setBounds((int)(rec.width*0.4),(int)(rec.height*0.9),80,20);
        button_cancel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.9),80,20);
    }

    void update(){
        if(post != null && post.size() > 0){
            for(Object[] obj : post){
                if(obj[0].toString().equals(no)){
                    this.text_name.setText(obj[1].toString());
                    this.text_salary.setText(obj[2].toString());
                    this.area_workcontent.setText(obj[3].toString());
                    PostInfo.name = obj[1].toString();
                    break;
                }
            }
        }
    }

    void setfont(Font f){
        label_name.setFont(f);
        text_name.setFont(f);
        label_salary.setFont(f);
        text_salary.setFont(f);
        label_workcontent.setFont(f);
        area_workcontent.setFont(f);
        button_cancel.setFont(f);
        button_save.setFont(f);
    }
}
