package frame;

import control.logControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JOptionPane;

public class Log extends JFrame {
    JPanel mainPanel = new JPanel();
    JLabel logName = new JLabel();
    JLabel logPassword = new JLabel();
    JTextField name = new JTextField();
    JPasswordField password = new JPasswordField();
    JButton log = new JButton();
    JButton cancel = new JButton();
    Font font=new Font("宋体",Font.BOLD,24);

    public Log(){
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("登录");
        //this.setSize(300,200);
        this.setBounds(400,200,600,400);

        this.mainPanel.setLayout(null);
        //this.mainPanel.setBackground(Color.blue);

        this.mainPanel.add(logName);
        logName.setFont(font);
        //logName.setVisible(true);
        this.mainPanel.add(logPassword);
        logPassword.setFont(font);

        this.mainPanel.add(name);
        name.setFont(font);
        this.mainPanel.add(password);
        password.setFont(font);

        this.mainPanel.add(log);
        log.setFont(font);
        this.mainPanel.add(cancel);
        cancel.setFont(font);

        this.logName.setText("用户名");
        this.logPassword.setText("密码");
        this.log.setText("登录");
        this.cancel.setText("取消");
        this.setContentPane(mainPanel);
        //updateBounds();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //窗体大小变化监听事件
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //JOptionPane.showMessageDialog(null,"窗体变化","标题",JOptionPane.INFORMATION_MESSAGE);
                updateBounds();
            }
        });

        //“登录”按钮监听事件
        this.log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log();
            }
        });

        //“取消”按钮监听事件
        this.cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    public void updateBounds(){
        Rectangle bounds = this.getBounds();
        this.mainPanel.setBounds(0,0,bounds.width-10,bounds.height-10);
        //this.logName.setBounds(0,0,(int)(bounds.width*0.4),(int)(bounds.height*0.3));
        this.logName.setBounds((int)(bounds.width*0.25),(int)(bounds.height*0.2),100,30);
        this.logPassword.setBounds((int)(bounds.width*0.25),(int)(bounds.height*0.5),100,30);
        this.name.setBounds((int)(bounds.width*0.37)+100,(int)(bounds.height*0.2),150,30);
        this.password.setBounds((int)(bounds.width*0.37)+100,(int)(bounds.height*0.5),150,30);
        this.log.setBounds((int)(bounds.width*0.3),(int)(bounds.height*0.75),100,30);
        this.cancel.setBounds((int)(bounds.width*0.37)+100,(int)(bounds.height*0.75),100,30);
    }

    public void log(){
        //登录事件
        String userName = name.getText().replace(" ","");
        String pass = new String(password.getPassword());
        if(userName != null && pass != null) {
            if (userName.split(" ").equals("") || pass.split(" ").equals("") || userName.length() <=0 || pass.length() <= 0) {
                JOptionPane.showMessageDialog(null, "用户名及密码不能为空", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            logControl logc = new logControl();
            boolean logStatus = logc.searchUser(userName, pass);
            if (logc.isUser == false) {
                JOptionPane.showMessageDialog(null, "用户名或密码错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            } else if (logc.isUser == true && logStatus == false) {
                JOptionPane.showMessageDialog(null, "密码错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
            else{
               // System.out.println(logc.isHR);
                MainFrame main = new MainFrame(logc.isManager,logc.isHR,userName);
                this.dispose();
            }
        }
    }

    public void cancel(){
        this.name.setText("");
        this.password.setText("");
    }

    public static void main(String[] args){
        try {
            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        }catch (Exception e){
            //
        }
        Log log1 = new Log();
    }

}

