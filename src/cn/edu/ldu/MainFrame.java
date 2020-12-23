package cn.edu.ldu;

import com.sun.mail.util.MailSSLSocketFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail .Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yk
 */
public class MainFrame extends javax.swing.JFrame {

    private int Reva = 0;
    /**
     * Creates new form MainFrame
     */
    private String userAddr = null;
    private String userPass = null;
    private String smtpAddr = null;
    private String pop3Addr = null;
    private StringBuffer bodytext=new StringBuffer();;
    private Message[] messages;

    public MainFrame(String userAddr, String userPass, String smtpAddr, String pop3Addr) {
        initComponents();
        this.userAddr = userAddr;
        this.userPass = userPass;
        this.smtpAddr = smtpAddr;
        this.pop3Addr = pop3Addr;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mailTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        btnReserve = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("收件箱--唐玉凯设计");

        mailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "发件人", "主题"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mailTable.setRowHeight(30);
        mailTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mailTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mailTable);

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setLineWrap(true);
        txtArea.setRows(5);
        jScrollPane2.setViewportView(txtArea);

        btnSend.setText("发信");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnReserve.setText("收信");
        btnReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReserveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSend)
                    .addComponent(btnReserve, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSend)
                        .addGap(35, 35, 35)
                        .addComponent(btnReserve))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mailTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mailTableMouseClicked
        try {
            // TODO add your handling code here:
            int row = Reva - ((JTable) evt.getSource()).rowAtPoint(evt.getPoint())-1;
            if(!messages[row].getFolder().isOpen()) //判断是否open   
                messages[row].getFolder().open(Folder.READ_WRITE);
            String from=messages[row].getFrom()[0].toString();
            String [] temp1=from.split("<",2);
            from="<"+temp1[temp1.length-1];
            String subject=messages[row].getSubject();
            bodytext.setLength(0);
            getMailContent((Part)messages[row]);
            this.txtArea.setText("发件人："+from+"\n主题："+subject+"\n正文：\n"+bodytext);
        }catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_mailTableMouseClicked

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new SendFrame(userAddr, userPass, smtpAddr, pop3Addr).setVisible(true);
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReserveActionPerformed
        try {
            //清空表格内容
            DefaultTableModel tableModel = (DefaultTableModel) mailTable.getModel();
            tableModel.setRowCount(0);
            // TODO add your handling code here:
            String userName;
            String[] temp = userAddr.split("@", 2);
            userName = temp[0];
            // 创建一个有具体连接信息的Properties对象
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "pop3");
            props.put("mail.smtp.ssl.enable", "true");
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.setProperty("mail.pop3.host", pop3Addr);
            props.put("mail.smtp.ssl.socketFactory", sf);
            // 使用Properties对象获得Session对象
            Session session = Session.getInstance(props);
            // 利用Session对象获得Store对象，并连接pop3服务器
            Store store = session.getStore();
            store.connect(pop3Addr, userName, userPass);
            // 获得邮箱内的邮件夹Folder对象，以"只读"打开
            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);
            // 获得邮件夹Folder内的所有邮件Message对象
            messages = folder.getMessages();
            int count = messages.length;
            Reva = count;
            for (int i = count-1 ; i >= count-10; i--) {
                //表格填充数据
                String from=messages[i].getFrom()[0].toString();
                String [] temp1=from.split("<",2);
                from="<"+temp1[temp1.length-1];
                tableModel.addRow(new Object[]{from,messages[i].getSubject()});
            }
            //关闭 Folder 会真正删除邮件, false 不删除 
            folder.close(false);
             //关闭 store, 断开网络连接
            store.close();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "邮件接收失败", "错误提示", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReserveActionPerformed

    public void getMailContent(Part part) throws Exception {   
        String contenttype = part.getContentType();   
        int nameIndex = contenttype.indexOf("name");
        boolean flag = false;   
        if (nameIndex != -1)   
            flag = true;   
        if (part.isMimeType("text/plain") && !flag) {   
            bodytext.append((String) part.getContent());   
        } else if (part.isMimeType("text/html") && !flag) {   
            bodytext.append((String) part.getContent());   
        } else if (part.isMimeType("multipart/*")) {   
            Multipart multipart = (Multipart) part.getContent();   
            int counts = multipart.getCount();   
            for (int i = 0; i < counts; i++) {
                getMailContent(multipart.getBodyPart(i));   
            }   
        } else if (part.isMimeType("message/rfc822")) {   
            getMailContent((Part) part.getContent());   
        }   
    } 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReserve;
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable mailTable;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the bodytext
     */
    public StringBuffer getBodytext() {
        return bodytext;
    }
}
