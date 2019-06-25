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

public class AddDepart extends JFrame{
    public java.util.List<Object[]> employee;
    public java.util.List<Object[]> department;
    public java.util.List<Object[]> post;
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

    public AddDepart(java.util.List<Object[]> em, java.util.List<Object[]> de, java.util.List<Object[]> po){
        this.employee = em;
        this.department = de;
        this.post = po;
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("添加部门");
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
                //获取上级部门编号
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
                String deno = "";
                int k = 1;
                do{
                    boolean isExist = false;
                    for(Object[] obj : department){
                        if(Integer.parseInt(obj[0].toString()) == k){
                            k++;
                            isExist = true;
                            break;
                        }
                    }
                    if(!isExist){
                        break;
                    }
                }while(true);
                if(k < 10){
                    String a = ""+k;
                    deno = "0"+a;
                }
                else{
                    deno = k+"";
                }
                //获取新的经理编号
                String managerno = "";
                if(post != null && post.size() > 0){
                    for(Object[] obj : post){
                        if(obj[0].toString().equals(manager.substring(2,4)) && obj[1].toString().equals("经理")){
                            JOptionPane.showMessageDialog(null, "该职工是其他部门经理", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(obj[1].toString().equals("经理")) {
                            managerno = deno + obj[0].toString();
                        }
                    }
                }

                Department depart = new Department(deno,name,managerno+"01",place,higherno,content);
                Depart.addDepart(managerno+"01",manager,depart);
                dispose();
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
        button_save.setBounds((int)(rec.width*0.4),(int)(rec.height*0.9),100,20);
        button_cancel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.9),100,20);
    }

    void update(){
        this.text_name.setText("");
        this.text_workplace.setText("");
        this.area_workcontent.setText("");
        if(employee != null && employee.size()>0){
            for(Object[] obj : employee){
                combo_manager.addItem(obj[1].toString()+obj[0].toString());
            }
        }
        if(department != null && department.size()>0){
            for(Object[] obj : department){
                combo_higher.addItem(obj[1].toString());
            }
        }
    }

    void setfont(Font f){
        label_workcontent.setFont(f);
        area_workcontent.setFont(f);
        label_manager.setFont(f);
        combo_manager.setFont(f);
        label_higher.setFont(f);
        combo_higher.setFont(f);
        label_workplace.setFont(f);
        text_workplace.setFont(f);
        label_name.setFont(f);
        text_name.setFont(f);
        button_save.setFont(f);
        button_cancel.setFont(f);
    }
}
