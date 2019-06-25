package frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Vector;

public class CheckInfo extends JFrame {
    JPanel mainPanel = new JPanel();
    JTable table_checkInfo = new JTable();
    Font font=new Font("宋体",Font.BOLD,18);

    public CheckInfo(List<Object[]> checkHistory,String no) {
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("考勤历史记录");

        this.setBounds(400,200,600,400);

        this.mainPanel.setLayout(null);
        //this.mainPanel.setBackground(Color.blue);
        this.setContentPane(mainPanel);

        this.mainPanel.add(table_checkInfo);
        table_checkInfo.setFont(font);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Rectangle rec = mainPanel.getBounds();
                table_checkInfo.setBounds(0,0,rec.width,rec.height);
            }
        });

        Vector vData = new Vector();
        Vector vName = new Vector();

        vName.add("日期");
        vName.add("签到时间");
        vName.add("签退时间");
        vName.add("考勤状态");
        vData.add(vName.clone());
        if (checkHistory != null && checkHistory.size() > 0) {
            for (Object[] obj : checkHistory) {
                if (obj[0].equals(no)) {
                    Vector vRow = new Vector();
                    vRow.add(obj[1]);
                    vRow.add(obj[2]);
                    vRow.add(obj[3]);
                    vRow.add(obj[4]);
                    vData.add(vRow.clone());
                }
            }
            DefaultTableModel model = new DefaultTableModel(vData, vName);
            table_checkInfo.setModel(model);
        }
    }
}
