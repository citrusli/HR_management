package frame;

import control.*;
import control.Post;
import entity.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class MainFrame extends JFrame {
    JPanel panel_main = new JPanel();
    JPanel panel_Guide = new JPanel();
    JLabel selfManage = new JLabel("个人管理");
    JLabel departManage = new JLabel("部门管理");
    JLabel employManage = new JLabel("职工管理");
   // JTabbedPane tabbedPane_manage = new JTabbedPane();
    private String employNo;
    private String employPass;
    private boolean isManager;
    private boolean isHR;
    boolean isExist = false;
    //个人管理
    public List<Object[]> employee;
    public List<Object[]> checkHistory;
    public List<Object[]> department;
    public List<Object[]> post;
    public List<Object[]> record;
    public List<Object[]> salary;

    Font font=new Font("宋体",Font.BOLD,18);
    //个人管理
    //JPanel panel_Self = new JPanel();
    JLabel label_selfInfo = new JLabel("个人信息");
    JLabel label_selfSalary = new JLabel("工资管理");
    JLabel label_selfRecord = new JLabel("档案管理");
    //个人信息
    JPanel panel_selfInfo = new JPanel();
    JLabel label_selfNo = new JLabel("编号");
    JTextField text_selfNo = new JTextField();
    JLabel label_selfName = new JLabel("姓名");
    JTextField text_selfName = new JTextField();
    JLabel label_selfGender = new JLabel("性别");
    JTextField text_selfGender = new JTextField();
    JLabel label_selfAge = new JLabel("年龄");
    JTextField text_selfAge = new JTextField();
    JLabel label_selfStatus = new JLabel("状态");
    JTextField text_selfstatus = new JTextField();
    JLabel label_selfTel = new JLabel("联系方式");
    JTextField text_selfTel = new JTextField();
    JButton button_selfInfoEdit = new JButton("编辑");
    JButton button_selfInfoSave = new JButton("保存");
    JButton button_selfInfoCancel = new JButton("取消");
   // Object[] columnNames_check = {"日期", "签到时间", "签退时间", "考勤状态"};
    JTable table_check = new JTable();
    JButton button_checkin = new JButton("签到");
    JButton button_checkout = new JButton("签退");
    JButton button_checkInfo = new JButton("历史记录");
    //工资管理
    JPanel panel_selfSalary = new JPanel();
    //Object[] columnNames_salary = {"编号","姓名","基本工资","岗位工资","保险","津贴","退休金","说明"};
    //String[][] da  = {{"","","","","","",""}};
   JTable table_selfSalary = new JTable();
    //档案管理
    JPanel panel_selfRecord = new JPanel();
    JLabel label_recordNo = new JLabel("编号");
    JTextField text_recordNo = new JTextField();
    JLabel label_recordName = new JLabel("姓名");
    JTextField text_recordName = new JTextField();
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
    JButton button_recordEdit = new JButton("编辑");
    JButton button_recordSave = new JButton("保存");
    JButton button_recordCancel = new JButton("取消");



    //部门管理
    //JPanel panel_Depart = new JPanel();
    JLabel label_departInfo = new JLabel("部门信息");
    JLabel label_departEmploy = new JLabel("部门职工");
    //部门信息
    JPanel panel_departInfo = new JPanel();
    JLabel label_departNo = new JLabel("编号");
    JTextField text_departNo = new JTextField();
    JLabel label_departName = new JLabel("部门名称");
    JTextField text_departName = new JTextField();
    JLabel label_departManager = new JLabel("经理");
    JComboBox<String> combo_departManager = new JComboBox<String>();
    JLabel label_departWorkPlace = new JLabel("工作地点");
    JTextField text_departWorkPlace = new JTextField();
    JLabel label_departWorkContent = new JLabel("工作内容");
    JTextField text_departWorkContent = new JTextField();
    JLabel label_departHigher = new JLabel("上级部门");
    JComboBox<String> combo_departHigher = new JComboBox<String>();
    JButton button_departInfoEdit = new JButton("编辑");
    JButton button_departInfoSave = new JButton("保存");
    JButton button_departInfoCancel = new JButton("取消");
    //部门职工
    JPanel panel_departEmploy = new JPanel();
    //Object[] columnNames_departInfo = {"编号","姓名","性别","年龄","联系方式"};
    JTable table_departEmploy = new JTable();

    //职工管理
    //JPanel panel_Employ = new JPanel();
    JLabel label_EmployInfo = new JLabel("职工信息");
    JLabel label_EmploySalary = new JLabel("工资信息");
    JLabel label_depart = new JLabel("部门信息");
    JLabel label_post = new JLabel("岗位信息");
    JLabel label_EmployCheck = new JLabel("考勤管理");
    //职工信息
    JPanel panel_EmployInfo = new JPanel();
    //Object[] columnNames_EmployInfo = {"编号","部门","岗位","姓名","性别","年龄","联系方式"};
    JTable table_EmployInfo = new JTable();
    JButton button_EmployInfoAdd = new JButton("添加职工");
    JButton button_EmployInfoEdit = new JButton("编辑");
    JButton button_EmployInfoDel = new JButton("删除");
    //JButton button_EmployInfoRetire = new JButton("退休");
    //工资信息
    JPanel panel_EmploySalaryInfo = new JPanel();
  //  Object[] columnNames_EmploySalary = {"编号","姓名","基本工资","岗位工资","保险","津贴","退休金","说明"};
    JTable table_EmploySalary = new JTable();
    JButton button_EmploySalaryEdit = new JButton("编辑");
    //JButton button_EmploySalarySave = new JButton("保存");
    //JButton button_EmploySalaryCancel = new JButton("取消");
    //部门信息
    JPanel panel_EmployDepartInfo = new JPanel();
    //Object[] columnNames_EmployDepartInfo = {"编号","名称","经理","上级部门","地点"};
    JTable table_EmployDepartInfo = new JTable();
    JButton button_EmployDepartInfoAdd = new JButton("添加");
    JButton button_EmployDepartInfoEdit = new JButton("编辑");
    JButton button_EmployDepartInfoDel = new JButton("删除");
    //岗位信息
    JPanel panel_EmployPostInfo = new JPanel();
    //Object[] columnNames_EmployPostInfo = {"编号","名称","工作内容","岗位工资"};
    JTable table_EmployPostInfo = new JTable();
    JButton button_EmployPostInfoAdd = new JButton("添加");
    JButton button_EmployPostInfoEdit = new JButton("编辑");
    JButton button_EmployPostInfoDel = new JButton("删除");
    //考勤管理
    JPanel panel_EmployCheckInfo = new JPanel();
    //Object[] columnNames_EmployCheckInfo = {"编号","姓名","考勤日期","签到时间","签退时间","考勤状态"};
    JTable table_EmployCheckInfo = new JTable();
    //JButton button_EmployCheckInfoEdit = new JButton("编辑");
    JButton button_EmployCheckInfoDel = new JButton("清除考勤信息");

    public MainFrame(boolean isManager, boolean isHR,String no){
        this.employNo = no;
        this.isHR = isHR;
        this.isManager = isManager;
        this.setLayout(null);
        this.setTitle("主界面");

        this.setLocationRelativeTo(null);
        this.panel_main.setLayout(null);
        this.setContentPane(panel_main);
        this.panel_Guide.setLayout(null);
        panel_main.add(panel_Guide);
        //panel_main.add(tabbedPane_manage);
        selfManage.setVisible(true);
        this.setBounds(200,100,800,600);
        initSelf();
        panel_Guide.add(selfManage);

        if(isManager) {
            initDepart();
            panel_Guide.add(departManage);
            departManage.setVisible(true);

           // tabbedPane_manage.addTab("部门管理",panel_departInfo);
        }
        if(isHR) {System.out.print("true");
            initEmploy();
            panel_Guide.add(employManage);
            employManage.setVisible(true);
           // tabbedPane_manage.addTab("职工管理",panel_EmployInfo);
        }

        updateSelfData();
        if(isManager){
            updateDepartData();
        }
        if(isHR){
            System.out.print("true");
            updateEmployData();
        }


        setFont();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setComVis(panel_selfInfo);

        this.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e) {
                setGuideBound();
                setSelfBound();
                if(isManager) setDepartBound();
                if(isHR) { employManage.setVisible(true); setEmployBound();}
            }
        });

        this.selfManage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateSelfData();
                label_selfRecord.setVisible(true);
                label_selfSalary.setVisible(true);
                label_selfInfo.setVisible(true);
                label_departInfo.setVisible(false);
                label_departEmploy.setVisible(false);
                label_EmployCheck.setVisible(false);
                label_EmployInfo.setVisible(false);
                label_EmploySalary.setVisible(false);
                label_depart.setVisible(false);
                label_post.setVisible(false);
                label_selfInfo.setBounds(10,selfManage.getY()+50,selfManage.getWidth(),50);
                label_selfSalary.setBounds(10,selfManage.getY()+100,selfManage.getWidth(),50);
                label_selfRecord.setBounds(10,selfManage.getY()+150,selfManage.getWidth(),50);
                if(isManager){
                    departManage.setBounds(10,selfManage.getY()+200,selfManage.getWidth(),50);
                }
                if(isHR && isManager){employManage.setBounds(10,selfManage.getY()+250,selfManage.getWidth(),50);
                }
                else if(isHR && !isManager){
                    employManage.setBounds(10,selfManage.getY()+200,selfManage.getWidth(),50);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.departManage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateSelfData();
                label_selfRecord.setVisible(false);
                label_selfSalary.setVisible(false);
                label_selfInfo.setVisible(false);
                label_departInfo.setVisible(true);
                label_departEmploy.setVisible(true);
                label_EmployCheck.setVisible(false);
                label_EmployInfo.setVisible(false);
                label_EmploySalary.setVisible(false);
                label_depart.setVisible(false);
                label_post.setVisible(false);
                departManage.setBounds(10,selfManage.getY()+50,selfManage.getWidth(),50);
                label_departInfo.setBounds(10,selfManage.getY()+100,selfManage.getWidth(),50);
                label_departEmploy.setBounds(10,selfManage.getY()+150,selfManage.getWidth(),50);
                if(isHR){employManage.setBounds(10,selfManage.getY()+200,selfManage.getWidth(),50);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.employManage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateSelfData();
                label_selfRecord.setVisible(false);
                label_selfSalary.setVisible(false);
                label_selfInfo.setVisible(false);
                label_departInfo.setVisible(false);
                label_departEmploy.setVisible(false);
                label_EmployCheck.setVisible(true);
                label_EmployInfo.setVisible(true);
                label_EmploySalary.setVisible(true);
                label_depart.setVisible(true);
                label_post.setVisible(true);
                if(isManager){
                    departManage.setBounds(10,selfManage.getY()+50,selfManage.getWidth(),50);
                    employManage.setBounds(10,selfManage.getY()+100,selfManage.getWidth(),50);
                    label_EmployInfo.setBounds(10,selfManage.getY()+150,selfManage.getWidth(),50);
                    label_depart.setBounds(10,selfManage.getY()+200,selfManage.getWidth(),50);
                    label_post.setBounds(10,selfManage.getY()+250,selfManage.getWidth(),50);
                    label_EmploySalary.setBounds(10,selfManage.getY()+300,selfManage.getWidth(),50);
                    label_EmployCheck.setBounds(10,selfManage.getY()+350,selfManage.getWidth(),50);
                }
                else {
                    employManage.setBounds(10, selfManage.getY() + 50, selfManage.getWidth(), 50);
                    label_EmployInfo.setBounds(10, selfManage.getY() + 100, selfManage.getWidth(), 50);
                    label_depart.setBounds(10, selfManage.getY() + 150, selfManage.getWidth(), 50);
                    label_post.setBounds(10, selfManage.getY() + 200, selfManage.getWidth(), 50);
                    label_EmploySalary.setBounds(10, selfManage.getY() + 250, selfManage.getWidth(), 50);
                    label_EmployCheck.setBounds(10, selfManage.getY() + 300, selfManage.getWidth(), 50);
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.label_selfInfo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setComVis(panel_selfInfo);
                //updateSelfData();
                updateSelfInfo();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.label_selfSalary.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setComVis(panel_selfSalary);
                updateSelfSalary();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.label_selfRecord.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setComVis(panel_selfRecord);
                updateSelfRecord();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.label_departInfo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setComVis(panel_departInfo);
                updateDepartInfo();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.label_departEmploy.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setComVis(panel_departEmploy);
                updateDepartEmploy();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.label_EmployInfo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setComVis(panel_EmployInfo);
                updateEmployInfo();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.label_depart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setComVis(panel_EmployDepartInfo);
                updateEmployDepart();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.label_post.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setComVis(panel_EmployPostInfo);
                updateEmployPost();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.label_EmploySalary.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setComVis(panel_EmploySalaryInfo);
                updateEmploySalary();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.label_EmployCheck.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setComVis(panel_EmployCheckInfo);
                updateEmployCheck();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.button_selfInfoEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSelfInfo();
            }
        });

        this.button_selfInfoCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text_selfAge.setEnabled(false);
                text_selfTel.setEnabled(false);
                text_selfName.setEnabled(false);
                button_selfInfoSave.setVisible(false);
                button_selfInfoCancel.setVisible(false);
                button_selfInfoEdit.setVisible(true);
                updateSelfInfo();
            }
        });

        this.button_selfInfoSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Exception er = new Exception();
                    String name = text_selfName.getText();
                    int age = Integer.parseInt(text_selfAge.getText());
                    String tel = text_selfTel.getText();
                    if(name.length() > 20) throw er;
                    if(age > 80 || age <=0) throw er;
                    if(tel.length() != 11) throw er;
                    for (char ch : tel.toCharArray()){
                        if(ch < '0' || ch > '9')
                            throw er;
                    }
                    Employee em = new Employee(employNo,name,text_selfGender.getText(),age,text_selfstatus.getText(),tel,employPass);
                    System.out.print(em.toString());
                    UpdateSelfInfo.updateEmploy(em);
                    text_selfAge.setEnabled(false);
                    text_selfTel.setEnabled(false);
                    text_selfName.setEnabled(false);
                    button_selfInfoSave.setVisible(false);
                    button_selfInfoCancel.setVisible(false);
                    button_selfInfoEdit.setVisible(true);
                    updateSelfData();
                }catch (Exception err){
                    JOptionPane.showMessageDialog(null, "输入格式错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }
        });

        this.button_checkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_checkin.setVisible(false);
                UpdateCheckin.updateChin(employNo);
                updateSelfData();
            }
        });

        this.button_checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_checkout.setVisible(false);
                UpdateCheckin.updateChout(employNo);
                updateSelfData();
            }
        });

        this.button_checkInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckInfo checkInfo = new CheckInfo(checkHistory,employNo);
            }
        });

        this.button_recordEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_recordEdit.setVisible(false);
                button_recordCancel.setVisible(true);
                button_recordSave.setVisible(true);
                text_recordBirthDate.setEnabled(true);
                text_recordDegree.setEnabled(true);
                text_recordEnterDate.setEnabled(true);
                text_recordID.setEnabled(true);
                text_recordName.setEnabled(true);
                combo_recordBirthPlace.setEnabled(true);
                combo_recordNation.setEnabled(true);
                area_recordAddress.setEnabled(true);
                area_recordEdu.setEnabled(true);
                area_recordNote.setEnabled(true);
            }
        });

        this.button_recordCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_recordEdit.setVisible(true);
                button_recordCancel.setVisible(false);
                button_recordSave.setVisible(false);
                text_recordBirthDate.setEnabled(false);
                text_recordDegree.setEnabled(false);
                text_recordEnterDate.setEnabled(false);
                text_recordID.setEnabled(false);
                text_recordName.setEnabled(false);
                combo_recordBirthPlace.setEnabled(false);
                combo_recordNation.setEnabled(false);
                area_recordAddress.setEnabled(false);
                area_recordEdu.setEnabled(false);
                area_recordNote.setEnabled(false);
                updateSelfRecord();
            }
        });

        this.button_recordSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Exception er = new Exception();
                    String birth = text_recordBirthDate.getText();
                    String nation = combo_recordNation.getSelectedItem().toString();
                    String place = combo_recordBirthPlace.getSelectedItem().toString();
                    String ID = text_recordID.getText();
                    String degree = text_recordDegree.getText();
                    String enter = text_recordEnterDate.getText();
                    String add = area_recordAddress.getText();
                    String edu = area_recordEdu.getText();
                    String note = area_recordNote.getText();
                    if(isIlegal(birth) && isIlegal(enter)) throw er;
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    Date birthdate = new Date(simpleDateFormat.parse( birth ).getTime());
                    Date enterdate = new Date(simpleDateFormat.parse( enter ).getTime());
                    if(ID.length() != 18) throw er;
                    for (char ch : ID.toCharArray()){
                        if(!((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')))
                            throw er;
                    }
                    Record re = new Record(employNo,birthdate,nation,place,ID,degree,enterdate,add,edu,note);
                    UpdateSelfRecord.updateRecord(re);
                    button_recordEdit.setVisible(true);
                    button_recordCancel.setVisible(false);
                    button_recordSave.setVisible(false);
                    text_recordBirthDate.setEnabled(false);
                    text_recordDegree.setEnabled(false);
                    text_recordEnterDate.setEnabled(false);
                    text_recordID.setEnabled(false);
                    text_recordName.setEnabled(false);
                    combo_recordBirthPlace.setEnabled(false);
                    combo_recordNation.setEnabled(false);
                    area_recordAddress.setEnabled(false);
                    area_recordEdu.setEnabled(false);
                    area_recordNote.setEnabled(false);
                    updateSelfData();
                }catch (Exception err){
                    JOptionPane.showMessageDialog(null, "输入格式错误", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        this.button_departInfoEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text_departWorkPlace.setEnabled(true);
                combo_departHigher.setEnabled(true);
                combo_departManager.setEnabled(true);
                text_departName.setEnabled(true);
                text_departWorkContent.setEnabled(true);
                button_departInfoCancel.setVisible(true);
                button_departInfoSave.setVisible(true);
                button_departInfoEdit.setVisible(false);
            }
        });

        this.button_departInfoCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text_departWorkPlace.setEnabled(false);
                combo_departHigher.setEnabled(false);
                combo_departManager.setEnabled(false);
                text_departName.setEnabled(false);
                text_departWorkContent.setEnabled(false);
                button_departInfoCancel.setVisible(false);
                button_departInfoSave.setVisible(false);
                button_departInfoEdit.setVisible(true);
                updateDepartInfo();
            }
        });

        this.button_departInfoSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldname = text_departName.getText();
                text_departWorkPlace.setEnabled(false);
                combo_departHigher.setEnabled(false);
                combo_departManager.setEnabled(false);
                text_departName.setEnabled(false);
                text_departWorkContent.setEnabled(false);
                button_departInfoCancel.setVisible(false);
                button_departInfoSave.setVisible(false);
                button_departInfoEdit.setVisible(true);
                String workplace = text_departWorkPlace.getText();
                String departHigher = combo_departHigher.getSelectedItem().toString();
                String higherno = "";
                String manager =combo_departManager.getSelectedItem().toString();
                String name = text_departName.getText();
                String workcontent = text_departWorkContent.getText();
                if(department != null && department.size() > 0){
                    for(Object[] obj : department){
                        if(obj[1].toString().equals(name) && !name.equals(oldname)){
                            JOptionPane.showMessageDialog(null, name+"已存在", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(oldname.equals("人事部")){
                            JOptionPane.showMessageDialog(null, "不可修改人事部门", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(obj[1].toString().equals(departHigher)){
                            higherno = obj[0].toString();
                        }
                    }
                }
                Department depart = new Department(text_departNo.getText(),name,manager.substring(manager.length()-6),workplace,higherno,workcontent);
                UpdateDepartInfo.updateDepart(depart);
                updateSelfData();
                updateDepartData();
            }
        });

        this.button_EmployInfoAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEmploy addEmploy = new AddEmploy(employee,checkHistory,department,post,record,salary);
                updateSelfData();
                updateEmployData();
            }
        });

        this.button_EmployInfoDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] select = table_EmployInfo.getSelectedRows();
                String[] del = new String[select.length];
                if(select == null || select.length <= 0){
                    return;
                }
                if(del.length > 0) {
                    for (int i = 0; i < select.length; i++) {
                        del[i] = table_EmployInfo.getValueAt(select[i], 0).toString();
                       // System.out.println(del[i]);
                    }
                    DelEmploy.del(del);
                }
            }
        });

        this.button_EmployInfoEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int select = table_EmployInfo.getSelectedRow();
                    String no = table_EmployInfo.getValueAt(select,0).toString();
                    EmployInfo employInfo = new EmployInfo(no,employee,checkHistory,department,post,record,salary);

                }catch (Exception err){
                    return;
                }
            }
        });

        this.button_EmployDepartInfoAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDepart addDepart = new AddDepart(employee,department,post);
            }
        });

        this.button_EmployDepartInfoDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] select = table_EmployDepartInfo.getSelectedRows();
                String[] del = new String[select.length];
                if(select == null || select.length <= 0){
                    return;
                }
                if(del.length > 0) {
                    for (int i = 0; i < select.length; i++) {
                        del[i] = table_EmployDepartInfo.getValueAt(select[i], 0).toString();
                        for(Object[] obj : employee){
                            if(obj[0].toString().substring(0,2).equals(del[i])){
                                JOptionPane.showMessageDialog(null, "所选部门有职工，无法删除", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }

                    Depart.delDepart(del);
                }
            }
        });

        this.button_EmployDepartInfoEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int select = table_EmployDepartInfo.getSelectedRow();
                    String no = table_EmployDepartInfo.getValueAt(select, 0).toString();
                    DepartInfo departInfo = new DepartInfo(no, employee, department, post);
                }catch (Exception err){
                    return;
                }
            }
        });

        this.button_EmployPostInfoAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPost addPost = new AddPost(post);
            }
        });

        this.button_EmployPostInfoDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] select = table_EmployPostInfo.getSelectedRows();
                String[] del = new String[select.length];
                if(select == null && select.length<=0)
                    return;
                if(del.length > 0) {
                    for (int i = 0; i < select.length; i++) {
                        del[i] = table_EmployPostInfo.getValueAt(select[i], 0).toString();
                        for(Object[] obj : employee){
                            if(obj[0].toString().substring(2,4).equals(del[i])){
                                JOptionPane.showMessageDialog(null, "所选岗位有职工，无法删除", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }
                    Post.delPost(del);
                }
            }
        });

        this.button_EmployPostInfoEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int select = table_EmployPostInfo.getSelectedRow();
                    String no = table_EmployPostInfo.getValueAt(select, 0).toString();
                    PostInfo departInfo = new PostInfo(no,  post);
                }catch (Exception err){
                    return;
                }
            }
        });

        this.button_EmploySalaryEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                    int select = table_EmploySalary.getSelectedRow();
                    String no = table_EmploySalary.getValueAt(select, 0).toString();
                    SalaryInfo salaryInfo = new SalaryInfo(no, salary,employee);
                }catch (Exception err){
                    System.out.print("error");
                   return;
                }
            }
        });

        this.button_EmployCheckInfoDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCheckin.delcheck();
            }
        });
    }

    boolean isIlegal(String date){
        String[] data = date.split("-");
        if(data.length == 3){
            try {
                int year = Integer.parseInt(data[0]);
                int month = Integer.parseInt(data[1]);
                int day = Integer.parseInt(data[2]);
                if(year <= 0 || year > 2019 || month <= 0 || month > 12|| day < 1 || day >12)
                    return false;
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    void editSelfInfo(){
        text_selfAge.setEnabled(true);
        text_selfTel.setEnabled(true);
        text_selfName.setEnabled(true);
        button_selfInfoSave.setVisible(true);
        button_selfInfoCancel.setVisible(true);
        button_selfInfoEdit.setVisible(false);
    }

    void initSelf(){
        //个人管理
        panel_Guide.add(label_selfInfo);
        label_selfSalary.setForeground(Color.gray);
        panel_Guide.add(label_selfSalary);
        label_selfInfo.setForeground(Color.gray);
        panel_Guide.add(label_selfRecord);
        label_selfRecord.setForeground(Color.gray);
        //个人信息
        this.panel_selfInfo.setLayout(null);
        panel_main.add(panel_selfInfo);
        panel_selfInfo.add(label_selfNo);
        panel_selfInfo.add(text_selfNo);
        text_selfNo.setEnabled(false);
        panel_selfInfo.add(label_selfName);
        panel_selfInfo.add(text_selfName);
        text_selfName.setEnabled(false);
        panel_selfInfo.add(label_selfGender);
        panel_selfInfo.add(text_selfGender);
        text_selfGender.setEnabled(false);
        panel_selfInfo.add(label_selfAge);
        panel_selfInfo.add(text_selfAge);
        text_selfAge.setEnabled(false);
        panel_selfInfo.add(label_selfStatus);
        panel_selfInfo.add(text_selfstatus);
        text_selfstatus.setEnabled(false);
        panel_selfInfo.add(label_selfTel);
        panel_selfInfo.add(text_selfTel);
        text_selfTel.setEnabled(false);
        panel_selfInfo.add(button_selfInfoEdit);
        panel_selfInfo.add(button_selfInfoSave);
        button_selfInfoSave.setVisible(false);
        panel_selfInfo.add(button_selfInfoCancel);
        button_selfInfoCancel.setVisible(false);
        panel_selfInfo.add(table_check);
        panel_selfInfo.add(button_checkin);
        panel_selfInfo.add(button_checkout);
        panel_selfInfo.add(button_checkInfo);
        //个人工资
        this.panel_selfSalary.setLayout(null);
        panel_main.add(panel_selfSalary);
        //table_selfSalary = new JTable(null,columnNames_salary);
        panel_selfSalary.add(table_selfSalary);
        //个人档案
        this.panel_selfRecord.setLayout(null);
        panel_main.add(panel_selfRecord);
        panel_selfRecord.add(label_recordNo);
        panel_selfRecord.add(text_recordNo);
        text_recordNo.setEnabled(false);
        panel_selfRecord.add(label_recordName);
        panel_selfRecord.add(text_recordName);
        text_recordName.setEnabled(false);
        panel_selfRecord.add(label_recordBirthDate);
        panel_selfRecord.add(text_recordBirthDate);
        panel_selfRecord.add(label_birthDateMessage);
        text_recordBirthDate.setEnabled(false);
        panel_selfRecord.add(label_recordNation);
        panel_selfRecord.add(combo_recordNation);
        combo_recordNation.setEnabled(false);
        panel_selfRecord.add(label_recordBirthPlace);
        panel_selfRecord.add(combo_recordBirthPlace);
        combo_recordBirthPlace.setEnabled(false);
        panel_selfRecord.add(label_recordID);
        panel_selfRecord.add(text_recordID);
        text_recordID.setEnabled(false);
        panel_selfRecord.add(label_recordDegree);
        panel_selfRecord.add(text_recordDegree);
        text_recordDegree.setEnabled(false);
        panel_selfRecord.add(label_recordEnterDate);
        panel_selfRecord.add(text_recordEnterDate);
        text_recordEnterDate.setEnabled(false);
        panel_selfRecord.add(label_EnterDateMessage);
        panel_selfRecord.add(label_recordAddress);
        panel_selfRecord.add(area_recordAddress);
        area_recordAddress.setEnabled(false);
        panel_selfRecord.add(label_recordEdu);
        panel_selfRecord.add(area_recordEdu);
        area_recordEdu.setEnabled(false);
        panel_selfRecord.add(label_recordNote);
        panel_selfRecord.add(area_recordNote);
        area_recordNote.setEnabled(false);
        panel_selfRecord.add(button_recordEdit);
        panel_selfRecord.add(button_recordSave);
        button_recordSave.setVisible(false);
        panel_selfRecord.add(button_recordCancel);
        button_recordCancel.setVisible(false);
    }

    public void initDepart() {
        //部门管理
        panel_Guide.add(label_departInfo);
        label_departInfo.setForeground(Color.gray);
        panel_Guide.add(label_departEmploy);
        label_departEmploy.setForeground(Color.gray);
        //部门信息
        panel_departInfo.setLayout(null);
        panel_main.add(panel_departInfo);
        panel_departInfo.add(label_departNo);
        panel_departInfo.add(text_departNo);
        text_departNo.setEnabled(false);
        panel_departInfo.add(label_departName);
        panel_departInfo.add(text_departName);
        text_departName.setEnabled(false);
        panel_departInfo.add(label_departManager);
        panel_departInfo.add(combo_departManager);
        combo_departManager.setEnabled(false);
        panel_departInfo.add(label_departWorkPlace);
        panel_departInfo.add(text_departWorkPlace);
        text_departWorkPlace.setEnabled(false);
        panel_departInfo.add(label_departWorkContent);
        panel_departInfo.add(text_departWorkContent);
        text_departWorkContent.setEnabled(false);
        panel_departInfo.add(label_departHigher);
        panel_departInfo.add(combo_departHigher);
        combo_departHigher.setEnabled(false);
        panel_departInfo.add(button_departInfoEdit);
        panel_departInfo.add(button_departInfoCancel);
        button_departInfoCancel.setVisible(false);
        panel_departInfo.add(button_departInfoSave);
        button_departInfoSave.setVisible(false);
        //部门职工
        panel_departEmploy.setLayout(null);
        panel_main.add(panel_departEmploy);
        panel_departEmploy.add(table_departEmploy);
    }

    public void initEmploy() {
        //职工管理
        panel_Guide.add(label_EmployInfo);
        label_EmployInfo.setForeground(Color.gray);
        panel_Guide.add(label_EmployCheck);
        label_EmployCheck.setForeground(Color.gray);
        panel_Guide.add(label_EmploySalary);
        label_EmploySalary.setForeground(Color.gray);
        panel_Guide.add(label_depart);
        label_depart.setForeground(Color.gray);
        panel_Guide.add(label_post);
        label_post.setForeground(Color.gray);
        //职工信息
        panel_EmployInfo.setLayout(null);
        panel_main.add(panel_EmployInfo);
        panel_EmployInfo.add(table_EmployInfo);
        panel_EmployInfo.add(button_EmployInfoEdit);
        panel_EmployInfo.add(button_EmployInfoDel);
        //panel_EmployInfo.add(button_EmployInfoRetire);
        panel_EmployInfo.add(button_EmployInfoAdd);
        //工资信息
        panel_EmploySalaryInfo.setLayout(null);
        panel_main.add(panel_EmploySalaryInfo);
        panel_EmploySalaryInfo.add(table_EmploySalary);
        panel_EmploySalaryInfo.add(button_EmploySalaryEdit);
        //部门信息
        panel_EmployDepartInfo.setLayout(null);
        panel_main.add(panel_EmployDepartInfo);
        panel_EmployDepartInfo.add(table_EmployDepartInfo);
        panel_EmployDepartInfo.add(button_EmployDepartInfoEdit);
        panel_EmployDepartInfo.add(button_EmployDepartInfoDel);
        panel_EmployDepartInfo.add(button_EmployDepartInfoAdd);
        //岗位信息
        panel_EmployPostInfo.setLayout(null);
        panel_main.add(panel_EmployPostInfo);
        panel_EmployPostInfo.add(table_EmployPostInfo);
        panel_EmployPostInfo.add(button_EmployPostInfoEdit);
        panel_EmployPostInfo.add(button_EmployPostInfoDel);
        panel_EmployPostInfo.add(button_EmployPostInfoAdd);
        //考勤管理
        panel_EmployCheckInfo.setLayout(null);
        panel_main.add(panel_EmployCheckInfo);
        panel_EmployCheckInfo.add(table_EmployCheckInfo);
        panel_EmployCheckInfo.add(button_EmployCheckInfoDel);
    }

    void setComVis(Component com){
        panel_selfInfo.setVisible(false);
        panel_selfSalary.setVisible(false);
        panel_selfRecord.setVisible(false);
        panel_departInfo.setVisible(false);
        panel_departEmploy.setVisible(false);
        panel_EmployInfo.setVisible(false);
        panel_EmploySalaryInfo.setVisible(false);
        panel_EmployCheckInfo.setVisible(false);
        panel_EmployPostInfo.setVisible(false);
        panel_EmployDepartInfo.setVisible(false);
        com.setVisible(true);
    }

    //更新左侧导航栏位置
    void setGuideBound() {
        label_selfInfo.setVisible(false);
        label_selfSalary.setVisible(false);
        label_selfRecord.setVisible(false);
        label_departEmploy.setVisible(false);
        label_departInfo.setVisible(false);
        label_EmployCheck.setVisible(false);
        label_EmployInfo.setVisible(false);
        label_post.setVisible(false);
        label_depart.setVisible(false);
        label_EmploySalary.setVisible(false);
        Rectangle rec = this.getBounds();
        panel_Guide.setBounds(0,0,(int)(rec.width*0.2),rec.height);
        selfManage.setBounds(10,10,(int)(rec.width*0.2),50);
        panel_selfInfo.setBounds((int)(rec.width*0.2),0,(int)(rec.width*0.8),rec.height);
        panel_selfSalary.setBounds((int)(rec.width*0.2),0,(int)(rec.width*0.8),rec.height);
        panel_selfRecord.setBounds((int)(rec.width*0.2),0,(int)(rec.width*0.8),rec.height);
        if(isManager) {
            panel_departInfo.setBounds((int) (rec.width * 0.2), 0, (int) (rec.width * 0.8), rec.height);
            panel_departEmploy.setBounds((int) (rec.width * 0.2), 0, (int) (rec.width * 0.8), rec.height);
            departManage.setBounds(10,60,(int)(rec.width*0.2),50);
           // departManage.setVisible(true);
        }
        if(isHR ) {
            panel_EmployInfo.setBounds((int) (rec.width * 0.2), 0, (int) (rec.width * 0.8), rec.height);
            panel_EmployDepartInfo.setBounds((int) (rec.width * 0.2), 0, (int) (rec.width * 0.8), rec.height);
            panel_EmployPostInfo.setBounds((int) (rec.width * 0.2), 0, (int) (rec.width * 0.8), rec.height);
            panel_EmployCheckInfo.setBounds((int) (rec.width * 0.2), 0, (int) (rec.width * 0.8), rec.height);
            panel_EmploySalaryInfo.setBounds((int) (rec.width * 0.2), 0, (int) (rec.width * 0.8), rec.height);
            if(isManager) {
                employManage.setBounds(10, 110, (int) (rec.width * 0.2), 50);
            }
            else{
                employManage.setBounds(10, 60, (int) (rec.width * 0.2), 50);
            }
        }
    }

    //更新个人信息控件位置
    void setSelfBound(){
        Rectangle rec = panel_selfInfo.getBounds();
        //个人信息
        label_selfNo.setBounds(0,(int)(rec.height*0.1),50,20);
        text_selfNo.setBounds((int)(rec.width*0.1),(int)(rec.height*0.1),100,20);
        label_selfName.setBounds((int)(rec.width*0.5),(int)(rec.height*0.1),50,20);
        text_selfName.setBounds((int)(rec.width*0.6),(int)(rec.height*0.1),100,20);
        label_selfGender.setBounds(0,(int)(rec.height*0.2),50,20);
        text_selfGender.setBounds((int)(rec.width*0.1),(int)(rec.height*0.2),100,20);
        label_selfAge.setBounds((int)(rec.width*0.5),(int)(rec.height*0.2),50,20);
        text_selfAge.setBounds((int)(rec.width*0.6),(int)(rec.height*0.2),100,20);
        label_selfStatus.setBounds(0,(int)(rec.height*0.3),50,20);
        text_selfstatus.setBounds((int)(rec.width*0.1),(int)(rec.height*0.3),100,20);
        label_selfTel.setBounds((int)(rec.width*0.5),(int)(rec.height*0.3),50,20);
        text_selfTel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.3),100,20);
        button_selfInfoEdit.setBounds((int)(rec.width*0.6),(int)(rec.height*0.4),80,20);
        button_selfInfoSave.setBounds((int)(rec.width*0.5),(int)(rec.height*0.4),80,20);
        button_selfInfoCancel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.4),80,20);
        table_check.setBounds(0,(int)(rec.height*0.5),(int)(rec.width*0.8),(int)(rec.height*0.2));
        button_checkin.setBounds((int)(rec.width*0.2),(int)(rec.height*0.8),80,20);
        button_checkout.setBounds((int)(rec.width*0.5),(int)(rec.height*0.8),80,20);
        button_checkInfo.setBounds((int)(rec.width*0.8),(int)(rec.height*0.8),100,20);
        //个人工资
        table_selfSalary.setBounds(0,(int)(rec.height*0.1),(int)(rec.width*0.8),200);
        //个人档案
        label_recordNo.setBounds(0,(int)(rec.height*0.1),50,20);
        text_recordNo.setBounds((int)(rec.width*0.1),(int)(rec.height*0.1),100,20);
        label_recordName.setBounds((int)(rec.width*0.5),(int)(rec.height*0.1),50,20);
        text_recordName.setBounds((int)(rec.width*0.6),(int)(rec.height*0.1),100,20);
        label_recordBirthDate.setBounds(0,(int)(rec.height*0.2),50,20);
        text_recordBirthDate.setBounds((int)(rec.width*0.1),(int)(rec.height*0.2),100,20);
        label_birthDateMessage.setBounds((int)(rec.width*0.5),(int)(rec.height*0.2),200,20);
        label_recordNation.setBounds(0,(int)(rec.height*0.3),50,20);
        combo_recordNation.setBounds((int)(rec.width*0.1),(int)(rec.height*0.3),100,20);
        label_recordBirthPlace.setBounds((int)(rec.width*0.5),(int)(rec.height*0.3),50,20);
        combo_recordBirthPlace.setBounds((int)(rec.width*0.6),(int)(rec.height*0.3),100,20);
        label_recordID.setBounds(0,(int)(rec.height*0.4),50,20);
        text_recordID.setBounds((int)(rec.width*0.1),(int)(rec.height*0.4),100,20);
        label_recordDegree.setBounds((int)(rec.width*0.5),(int)(rec.height*0.4),50,20);
        text_recordDegree.setBounds((int)(rec.width*0.6),(int)(rec.height*0.4),100,20);
        label_recordEnterDate.setBounds(0,(int)(rec.height*0.5),50,20);
        text_recordEnterDate.setBounds((int)(rec.width*0.1),(int)(rec.height*0.5),100,20);
        label_EnterDateMessage.setBounds((int)(rec.width*0.5),(int)(rec.height*0.5),100,20);
        label_recordAddress.setBounds(0,(int)(rec.height*0.6),50,20);
        area_recordAddress.setBounds((int)(rec.width*0.1),(int)(rec.height*0.6),(int)(rec.width*0.7),40);
        label_recordEdu.setBounds(0,(int)(rec.height*0.7),50,20);
        area_recordEdu.setBounds((int)(rec.width*0.1),(int)(rec.height*0.7),(int)(rec.width*0.7),40);
        label_recordNote.setBounds(0,(int)(rec.height*0.8),50,20);
        area_recordNote.setBounds((int)(rec.width*0.1),(int)(rec.height*0.8),(int)(rec.width*0.7),40);
        button_recordEdit.setBounds((int)(rec.width*0.6),(int)(rec.height*0.9),80,20);
        button_recordSave.setBounds((int)(rec.width*0.5),(int)(rec.height*0.9),80,20);
        button_recordCancel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.9),80,20);
    }

    //更新部门管理控件位置
    void setDepartBound(){
        Rectangle rec = panel_departInfo.getBounds();
        //部门信息
        label_departNo.setBounds(0,(int)(rec.height*0.1),50,20);
        text_departNo.setBounds((int)(rec.width*0.1),(int)(rec.height*0.1),100,20);
        label_departName.setBounds((int)(rec.width*0.5),(int)(rec.height*0.1),50,20);
        text_departName.setBounds((int)(rec.width*0.6),(int)(rec.height*0.1),100,20);
        label_departManager.setBounds(0,(int)(rec.height*0.2),50,20);
        combo_departManager.setBounds((int)(rec.width*0.1),(int)(rec.height*0.2),100,20);
        label_departWorkPlace.setBounds((int)(rec.width*0.5),(int)(rec.height*0.2),50,20);
        text_departWorkPlace.setBounds((int)(rec.width*0.6),(int)(rec.height*0.2),150,20);
        label_departWorkContent.setBounds(0,(int)(rec.height*0.3),50,20);
        text_departWorkContent.setBounds((int)(rec.width*0.1),(int)(rec.height*0.3),200,20);
        label_departHigher.setBounds((int)(rec.width*0.5),(int)(rec.height*0.3),50,20);
        combo_departHigher.setBounds((int)(rec.width*0.6),(int)(rec.height*0.3),200,20);
        button_departInfoEdit.setBounds((int)(rec.width*0.6),(int)(rec.height*0.5),80,20);
        button_departInfoSave.setBounds((int)(rec.width*0.5),(int)(rec.height*0.5),80,20);
        button_departInfoCancel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.5),80,20);
        //部门职工
        table_departEmploy.setBounds(0,(int)(rec.height*0.1),(int)(rec.width*0.9),(int)(rec.height*0.6));
    }

    //更新职工管理控件位置
    void setEmployBound(){
        Rectangle rec = panel_EmployInfo.getBounds();
        //职工信息
        table_EmployInfo.setBounds(0,(int)(rec.height*0.1),(int)(rec.width*0.9),(int)(rec.height*0.6));
        button_EmployInfoEdit.setBounds((int)(rec.width*0.4),(int)(rec.height*0.8),80,20);
        button_EmployInfoDel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.8),80,20);
       // button_EmployInfoRetire.setBounds((int)(rec.width*0.8),(int)(rec.height*0.8),80,20);
        button_EmployInfoAdd.setBounds((int)(rec.width*0.2),(int)(rec.height*0.8),80,20);
        //工资信息
        table_EmploySalary.setBounds(0,(int)(rec.height*0.1),(int)(rec.width*0.9),(int)(rec.height*0.6));
        button_EmploySalaryEdit.setBounds((int)(rec.width*0.6),(int)(rec.height*0.8),80,20);
        //部门信息
        table_EmployDepartInfo.setBounds(0,(int)(rec.height*0.1),(int)(rec.width*0.9),(int)(rec.height*0.6));
        button_EmployDepartInfoAdd.setBounds((int)(rec.width*0.2),(int)(rec.height*0.8),80,20);
        button_EmployDepartInfoEdit.setBounds((int)(rec.width*0.4),(int)(rec.height*0.8),80,20);
        button_EmployDepartInfoDel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.8),80,20);
        //岗位信息
        table_EmployPostInfo.setBounds(0,(int)(rec.height*0.1),(int)(rec.width*0.9),(int)(rec.height*0.6));
        button_EmployPostInfoAdd.setBounds((int)(rec.width*0.2),(int)(rec.height*0.8),80,20);
        button_EmployPostInfoEdit.setBounds((int)(rec.width*0.4),(int)(rec.height*0.8),80,20);
        button_EmployPostInfoDel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.8),80,20);
        //考勤管理
        table_EmployCheckInfo.setBounds(0,(int)(rec.height*0.1),(int)(rec.width*0.9),(int)(rec.height*0.6));
        button_EmployCheckInfoDel.setBounds((int)(rec.width*0.6),(int)(rec.height*0.8),80,20);
    }

    void updateSelfData(){
        SearchInfo searchInfo = new SearchInfo();
        searchInfo.searchSelf(employNo);
        employee = searchInfo.employee;
        checkHistory = searchInfo.checkHistory;
        department = searchInfo.department;
        post = searchInfo.post;
        record = searchInfo.record;
        salary = searchInfo.salary;
        updateSelfInfo();
        updateSelfRecord();
        updateSelfSalary();
    }

    void updateDepartData(){
        updateDepartInfo();
        updateDepartEmploy();
    }

    void updateEmployData(){
        updateEmployCheck();
        updateEmployDepart();
        updateEmployInfo();
        updateEmployPost();
        updateEmploySalary();
    }

    //更新个人信息
    void updateSelfInfo(){
        if(employee != null && employee.size() > 0){
            for(Object[] obj : employee){
                if(obj[0].toString().equals(employNo)){
                    text_selfNo.setText(obj[0].toString());
                    text_selfName.setText(obj[1].toString());
                    text_selfGender.setText(obj[2].toString());
                    text_selfAge.setText(obj[3].toString());
                    text_selfstatus.setText(obj[4].toString());
                    text_selfTel.setText(obj[5].toString());
                    employPass = obj[6].toString();
                    break;
                }
            }
        }
        Vector vData = new Vector();
        Vector vName = new Vector();
        Vector vRow = new Vector();
        vName.add("日期");
        vName.add("签到时间");
        vName.add("签退时间");
        vName.add("考勤状态");
        vData.add(vName.clone());
        Calendar cal = Calendar.getInstance();
        String day = cal.get(Calendar.YEAR) + "-" +(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
        if(checkHistory != null && checkHistory.size() > 0){
            for(Object[] obj : checkHistory){
                //System.out.println(obj[1].toString().substring(0,10) +"  "+day);
                String d = obj[1].toString().substring(0,10);
                if(obj[0].toString().equals(employNo) && (isDateEqual(day,d))){
                    isExist = true;
                    vRow.add(obj[1].toString().substring(0,10));
                    vRow.add(obj[2]);
                    if(obj[2] != null){
                        button_checkin.setVisible(false);
                    }
                    if(obj[3] != null){
                        button_checkout.setVisible(false);
                    }
                    vRow.add(obj[3]);
                    vRow.add(obj[4]);
                    vData.add(vRow.clone());
                    DefaultTableModel model = new DefaultTableModel(vData,vName);
                    table_check.setModel(model);
                    break;
                }
            }
        }
        if(!isExist){
            vRow.add(day);
            vRow.add("");
            vRow.add("");
            vRow.add("缺勤");
            vData.add(vRow.clone());
            DefaultTableModel model = new DefaultTableModel(vData,vName);
            table_check.setModel(model);
            InsertCheck.insertCheck(employNo);
        }
    }

    boolean isDateEqual(String date,String data){
        String[] stand = date.split("-");
        String[] da = data.split("-");
        if(Integer.parseInt(stand[0]) == Integer.parseInt(da[0]) && Integer.parseInt(stand[1]) == Integer.parseInt(da[1]) && Integer.parseInt(stand[2]) == Integer.parseInt(da[2]))
            return true;
        return false;
    }

    //更新个人工资
    void updateSelfSalary() {
        if(salary != null && salary.size() > 0){
            Vector vData = new Vector();
            Vector vName = new Vector();
            Vector vRow = new Vector();
            vName.add("编号");
            vName.add("姓名");
            vName.add("基本工资");
            vName.add("岗位工资");
            vName.add("保险");
            vName.add("津贴");
           // vName.add("退休金");
            vName.add("说明");
            vData.add(vName.clone());
            for(Object[] obj : salary){
                if(obj[0].toString().equals(employNo)){
                    //obj[0] = columnNames_salary;

                    Object[][] salary = new Object[2][8];
                    vRow.add(employNo);
                    vRow.add(text_selfName.getText());
                    vRow.add(obj[2]);
                    vRow.add(obj[3]);
                    vRow.add(obj[4]);
                    vRow.add(obj[5]);
                   // vRow.add(obj[6]);
                    vRow.add(obj[1]);
                    vData.add(vRow.clone());
                    DefaultTableModel model = new DefaultTableModel(vData,vName);
                    table_selfSalary.setModel(model);
                    break;
                }
            }
        }
    }

    //更新个人档案
    void updateSelfRecord() {
        if(record != null && record.size()>=0){
            for(Object[] obj : record){
                if(obj[0].toString().equals(employNo)){
                    text_recordNo.setText(employNo);
                    text_recordName.setText(text_selfName.getText());
                    text_recordID.setText(obj[4].toString());
                    text_recordBirthDate.setText(obj[1].toString());
                    text_recordDegree.setText(obj[5].toString());
                    text_recordEnterDate.setText(obj[6].toString());
                    combo_recordBirthPlace.setSelectedItem(obj[3].toString());
                    combo_recordNation.setSelectedItem(obj[2].toString());
                    area_recordNote.setText(obj[9].toString());
                    area_recordAddress.setText(obj[7].toString());
                    area_recordEdu.setText(obj[8].toString());
                    break;
                }
            }
        }
    }

    //更新部门管理部门信息
    void updateDepartInfo() {
        if(department != null && department.size() > 0) {
            for (Object[] obj : department) {
                if(obj[0].toString().equals(employNo.substring(0,2))){
                    text_departNo.setText(obj[0].toString());
                    text_departName.setText(obj[1].toString());
                    text_departWorkPlace.setText(obj[3].toString());
                    text_departWorkContent.setText(obj[5].toString());
                    int k = 0;
                    combo_departManager.removeAllItems();
                    String manager = "";
                    if (obj[2] != null){
                        for (Object[] objem : employee){
                            if (obj[2].toString().equals(objem[0].toString())) {
                                manager = objem[1].toString()+objem[0].toString();
                            }
                            if(obj[0].toString().equals(objem[0].toString().substring(0,2))){
                                combo_departManager.addItem(objem[1].toString() + objem[0].toString());
                            }
                        }
                    }
                    combo_departManager.setSelectedItem(manager);
                    combo_departHigher.removeAllItems();
                    String higherdepart = "";
                    if (obj[4] != null){
                        for (Object[] objem : department){
                            if (obj[4].toString().equals(objem[0].toString())) {
                                higherdepart = objem[1].toString();
                            }
                            combo_departHigher.addItem(objem[1].toString());
                        }
                    }
                    combo_departHigher.setSelectedItem(higherdepart);
                    break;
                }
            }
        }
    }

    //更新部门管理职工信息
    void updateDepartEmploy() {
        Vector vData = new Vector();
        Vector vName = new Vector();

        vName.add("编号");
        vName.add("姓名");
        vName.add("性别");
        vName.add("年龄");
        vName.add("联系方式");
        vData.add(vName.clone());
        if(employee != null && employee.size() > 0){
            for(Object[] obj : employee){
                if(obj[0].toString().substring(0,2).equals(employNo.substring(0,2))){
                    Vector vRow = new Vector();
                    vRow.add(obj[0]);
                    vRow.add(obj[1]);
                    vRow.add(obj[2]);
                    vRow.add(obj[3]);
                    vRow.add(obj[5]);
                    vData.add(vRow.clone());
                }
            }
            DefaultTableModel model = new DefaultTableModel(vData,vName);
            table_departEmploy.setModel(model);
        }
    }

    //更新职工管理职工信息
    void updateEmployInfo() {
        Vector vData = new Vector();
        Vector vName = new Vector();

        vName.add("编号");
        vName.add("姓名");
        vName.add("部门");
        vName.add("岗位");
        vName.add("性别");
        vName.add("年龄");
        vName.add("联系方式");
        vData.add(vName.clone());
        if(employee != null && employee.size() > 0){
            for(Object[] obj : employee){
                    Vector vRow = new Vector();
                    vRow.add(obj[0]);
                    vRow.add(obj[1]);
                    for(Object[] objde : department){
                        if(obj[0].toString().substring(0,2).equals(objde[0].toString())){
                            vRow.add(objde[1].toString());
                            break;
                        }
                    }
                for(Object[] objpost : post){
                    if(obj[0].toString().substring(2,4).equals(objpost[0].toString())){
                        vRow.add(objpost[1].toString());
                        break;
                    }
                }
                    vRow.add(obj[2]);
                    vRow.add(obj[3]);
                    vRow.add(obj[5]);
                    vData.add(vRow.clone());
                }
            DefaultTableModel model = new DefaultTableModel(vData,vName);
            table_EmployInfo.setModel(model);
        }
    }

    //更新职工管理部门信息
    void updateEmployDepart() {
        Vector vData = new Vector();
        Vector vName = new Vector();
        vName.add("编号");
        vName.add("名称");
        vName.add("经理");
        vName.add("上级部门");
        vName.add("地点");
        vData.add(vName.clone());
        if(department != null && department.size() > 0){
            for(Object[] obj : department){
                    Vector vRow = new Vector();
                    vRow.add(obj[0]);
                    vRow.add(obj[1]);
                for(Object[] objmana : employee){
                    if(obj[2] != null && obj[2].toString().equals(objmana[0].toString())){
                        vRow.add(objmana[1]);
                        break;
                    }
                }
                for(Object[] objde : department){
                    if(obj[4].toString().equals(objde[0].toString())){
                        vRow.add(objde[1]);
                        break;
                    }
                }
                    vRow.add(obj[3]);
                    vData.add(vRow.clone());
                }
            DefaultTableModel model = new DefaultTableModel(vData,vName);
            table_EmployDepartInfo.setModel(model);
        }
    }

    //更新职工管理岗位信息
    void updateEmployPost() {
        Vector vData = new Vector();
        Vector vName = new Vector();
        vName.add("编号");
        vName.add("名称");
        vName.add("工作内容");
        vName.add("岗位工资");
        vData.add(vName.clone());
        if(post != null && post.size() > 0){
            for(Object[] obj : post){
                Vector vRow = new Vector();
                vRow.add(obj[0]);
                vRow.add(obj[1]);
                vRow.add(obj[3]);
                vRow.add(obj[2]);
                vData.add(vRow.clone());
            }
            DefaultTableModel model = new DefaultTableModel(vData,vName);
            table_EmployPostInfo.setModel(model);
        }
    }

    //更新职工管理工资信息
    void updateEmploySalary() {
        Vector vData = new Vector();
        Vector vName = new Vector();
        vName.add("编号");
        vName.add("姓名");
        vName.add("基本工资");
        vName.add("岗位工资");
        vName.add("保险");
        vName.add("津贴");
        vName.add("说明");
        vData.add(vName.clone());
        if(salary != null && salary.size() > 0){
            for(Object[] obj : salary){
                Vector vRow = new Vector();
                vRow.add(obj[0]);
                for(Object[] objmana : employee){
                    if(obj[0].toString().equals(objmana[0].toString())){
                        vRow.add(objmana[1]);
                        break;
                    }
                }
                vRow.add(obj[2]);
                vRow.add(obj[3]);
                vRow.add(obj[4]);
                vRow.add(obj[5]);
                vRow.add(obj[1]);
                vData.add(vRow.clone());
            }
            DefaultTableModel model = new DefaultTableModel(vData,vName);
            table_EmploySalary.setModel(model);
        }
    }

    //更新职工管理考勤信息
    void updateEmployCheck() {
        Vector vData = new Vector();
        Vector vName = new Vector();

        vName.add("编号");
        vName.add("姓名");
        vName.add("日期");
        vName.add("签到时间");
        vName.add("签退时间");
        vName.add("考勤状态");
        vData.add(vName.clone());
        if(checkHistory != null && checkHistory.size() > 0){
            for(Object[] obj : checkHistory){
                Vector vRow = new Vector();
                String d = obj[1].toString().substring(0,10);
                vRow.add(obj[0]);
                for(Object[] objem : checkHistory){
                    if(obj[0].toString().equals(objem[0].toString())){
                        vRow.add(objem[1]);
                        break;
                    }
                }
                    vRow.add(obj[1].toString().substring(0,10));
                    vRow.add(obj[2]);
                    vRow.add(obj[3]);
                    vRow.add(obj[4]);
                    vData.add(vRow.clone());
            }
            DefaultTableModel model = new DefaultTableModel(vData,vName);
            table_EmployCheckInfo.setModel(model);
        }
    }

    //设置界面字体
    void setFont() {
        selfManage.setFont(font);
        departManage.setFont(font);
        employManage.setFont(font);
        label_selfInfo.setFont(font);
        label_selfSalary.setFont(font);
        label_selfRecord.setFont(font);
        //个人信息
        label_selfNo.setFont(font);
        text_selfNo.setFont(font);
        label_selfName.setFont(font);
        text_selfName.setFont(font);
        label_selfGender.setFont(font);
        text_selfGender.setFont(font);
        label_selfAge.setFont(font);
        text_selfAge.setFont(font);
        label_selfStatus.setFont(font);
        text_selfstatus.setFont(font);
        label_selfTel.setFont(font);
        text_selfTel.setFont(font);
        button_selfInfoEdit.setFont(font);
        button_selfInfoSave.setFont(font);
        button_selfInfoCancel.setFont(font);
        table_check.setFont(font);
        button_checkin.setFont(font);
        button_checkout.setFont(font);
        button_checkInfo.setFont(font);
        //工资管理
        table_selfSalary.setFont(font);
        //档案管理
        label_recordNo.setFont(font);
        text_recordNo.setFont(font);
        label_recordName.setFont(font);
        text_recordName.setFont(font);
        label_recordBirthDate.setFont(font);
        text_recordBirthDate.setFont(font);
        label_birthDateMessage.setFont(font);
        label_recordNation.setFont(font);
        combo_recordNation.setFont(font);
        label_recordBirthPlace.setFont(font);
        combo_recordBirthPlace.setFont(font);
        label_recordID.setFont(font);
        text_recordID.setFont(font);
        label_recordDegree.setFont(font);
        text_recordDegree.setFont(font);
        label_recordEnterDate.setFont(font);
        text_recordEnterDate.setFont(font);
        label_EnterDateMessage.setFont(font);
        label_recordAddress.setFont(font);
        area_recordAddress.setFont(font);
        label_recordEdu.setFont(font);
        area_recordEdu.setFont(font);
        label_recordNote.setFont(font);
        area_recordNote.setFont(font);
        button_recordEdit.setFont(font);
        button_recordSave.setFont(font);
        button_recordCancel.setFont(font);



        //部门管理
        label_departInfo.setFont(font);
        label_departEmploy.setFont(font);
        //部门信息
        label_departNo.setFont(font);
        text_departNo.setFont(font);
        label_departName.setFont(font);
        text_departName.setFont(font);
        label_departManager.setFont(font);
        combo_departManager.setFont(font);
        label_departWorkPlace.setFont(font);
        text_departWorkPlace.setFont(font);
        label_departWorkContent.setFont(font);
        text_departWorkContent.setFont(font);
        label_departHigher.setFont(font);
        combo_departHigher.setFont(font);
        button_departInfoEdit.setFont(font);
        button_departInfoSave.setFont(font);
        button_departInfoCancel.setFont(font);
        //部门职工
        table_departEmploy.setFont(font);

        //职工管理
        label_EmployInfo.setFont(font);
        label_EmploySalary.setFont(font);
        label_depart.setFont(font);
        label_post.setFont(font);
        label_EmployCheck.setFont(font);
        //职工信息
        table_EmployInfo.setFont(font);
        button_EmployInfoAdd.setFont(font);
        button_EmployInfoEdit.setFont(font);
        button_EmployInfoDel.setFont(font);
        //工资信息
        table_EmploySalary.setFont(font);
        button_EmploySalaryEdit.setFont(font);
        //部门信息
        table_EmployDepartInfo.setFont(font);
        button_EmployDepartInfoEdit.setFont(font);
        button_EmployDepartInfoDel.setFont(font);
        button_EmployDepartInfoAdd.setFont(font);
        //岗位信息
        table_EmployPostInfo.setFont(font);
        button_EmployPostInfoEdit.setFont(font);
        button_EmployPostInfoDel.setFont(font);
        button_EmployPostInfoAdd.setFont(font);
        //考勤管理
        table_EmployCheckInfo.setFont(font);
        button_EmployCheckInfoDel.setFont(font);
    }

}
