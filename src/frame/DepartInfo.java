package frame;

import com.sun.xml.fastinfoset.OctetBufferListener;
import control.Depart;
import entity.Department;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class DepartInfo extends JFrame{
    public java.util.List<Object[]> employee;
    public java.util.List<Object[]> department;
    public java.util.List<Object[]> post;
    public static String deno;
    JPanel mainPanel = new JPanel();
    Font font=new Font("宋体",Font.BOLD,18);
    JLabel label_name = new JLabel("名称");
    JTextField text_name = new JTextField();
    JLabel label_manager = new JLabel("经理");
    JComboBox<String> combo_manager = new JComboBox<String>();
    JLabel label_higher = new JLabel("上级部门");
    JComboBox<String> combo_higher = new JComboBox<>();
    JLabel label_workplace = new JLabel("工作地点");
    JTextField text_workplace = new JTextField();
    JLabel label_workcontent = new JLabel("工作内容");
    JTextArea area_workcontent = new JTextArea();
    JButton button_save = new JButton("确定");
    JButton button_cancel = new JButton("取消");

    public DepartInfo(String no,java.util.List<Object[]> em, java.util.List<Object[]> de, java.util.List<Object[]> po){
        this.deno = no;
        this.employee = em;
        this.department = de;
        this.post = po;
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("部门信息");
        this.mainPanel.setLayout(null);
        this.setBounds(400, 200, 700, 600);

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
                String place = text_workplace.getText();
                String content = area_workcontent.getText();
                String manager = combo_manager.getSelectedItem().toString();
                manager = manager.substring(manager.length()-6);
                String higher = combo_higher.getSelectedItem().toString();
                if(name == ""){
                    JOptionPane.showMessageDialog(null, "部门名不能为空", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String higherno = "";

                if(department != null && department.size() > 0){
                    for(Object[] obj : department){
                        if(obj[1].toString().equals(name)){
                            JOptionPane.showMessageDialog(null, "该部门已存在", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(higher.equals(obj[1].toString())){
                            higherno = obj[0].toString();
                        }
                    }
                }
                String managerno = "";
                if(post != null && post.size() > 0){
                    for(Object[] obj : post){
                        if(obj[1].toString().equals("经理")){
                            managerno = manager.substring(0,2)+obj[0].toString()+manager.substring(4,6);
                        }
                    }
                }

                Department depart = new Department(deno,name,managerno,place,higherno,content);
                Depart.updateDepart(depart);
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    void init(){
        mainPanel.add(label_name);
        mainPanel.add(text_name);
        mainPanel.add(label_higher);
        mainPanel.add(combo_higher);
        mainPanel.add(label_manager);
        mainPanel.add(combo_manager);
        mainPanel.add(label_workcontent);
        mainPanel.add(area_workcontent);
        mainPanel.add(label_workplace);
        mainPanel.add(text_workplace);
        mainPanel.add(button_cancel);
        mainPanel.add(button_save);
    }

    void setPanelBound(){
        Rectangle rec = mainPanel.getBounds();
        label_name.setBounds((int)(rec.width*0.1),(int)(rec.height*0.2),50,20);
        text_name.setBounds((int)(rec.width*0.2),(int)(rec.height*0.2),100,20);
        label_workplace.setBounds((int)(rec.width*0.5),(int)(rec.height*0.2),50,20);
        text_workplace.setBounds((int)(rec.width*0.6),(int)(rec.height*0.2),150,20);
        label_higher.setBounds((int)(rec.width*0.1),(int)(rec.height*0.4),50,20);
        combo_higher.setBounds((int)(rec.width*0.2),(int)(rec.height*0.4),100,20);
        label_manager.setBounds((int)(rec.width*0.5),(int)(rec.height*0.4),50,20);
        combo_manager.setBounds((int)(rec.width*0.6),(int)(rec.height*0.4),100,20);
        label_workcontent.setBounds((int)(rec.width*0.1),(int)(rec.height*0.6),50,20);
        area_workcontent.setBounds((int)(rec.width*0.2),(int)(rec.height*0.6),(int)(rec.width*0.6),100);
        button_save.setBounds((int)(rec.width*0.4),(int)(rec.height*0.9),80,20);
        button_cancel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.9),80,20);
    }

    void update(){

        if(employee != null && employee.size()>0){
            for(Object[] obj : employee){
                if(obj[0].toString().substring(0,2).equals(deno))
                combo_manager.addItem(obj[1].toString()+obj[0].toString());
            }
        }
        if(department != null && department.size()>0){
            for(Object[] obj : department){
                if (obj[0].toString().equals(deno)) {
                    if(obj[2] != null && obj[2].toString() != ""){
                        for(Object[] objem : employee){
                            if(obj[2].toString().equals(objem[0].toString()))
                                combo_manager.setSelectedItem(objem[1].toString()+objem[0].toString());
                        }
                    }
                    if(obj[4] != null && obj[4].toString() != ""){
                        for(Object[] objem : department){
                            if(obj[4].toString().equals(objem[0].toString()))
                                combo_higher.setSelectedItem(objem[1].toString());
                        }
                    }
                    this.text_name.setText(obj[1].toString());
                    this.text_workplace.setText(obj[3].toString());
                    this.area_workcontent.setText(obj[5].toString());
                    break;
                }
                combo_higher.addItem(obj[1].toString());
            }
        }
    }

    void setfont(Font f){
        label_workcontent.setFont(f);
        area_workcontent.setFont(f);
        label_higher.setFont(f);
        combo_higher.setFont(f);
        label_manager.setFont(f);
        combo_manager.setFont(f);
        label_name.setFont(f);
        text_name.setFont(f);
        label_workplace.setFont(f);
        text_workplace.setFont(f);
        button_cancel.setFont(f);
        button_save.setFont(f);
    }
}
