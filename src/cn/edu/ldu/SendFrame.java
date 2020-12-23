package cn.edu.ldu;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author yk
 */
public class SendFrame extends javax.swing.JFrame {

    private String userAddr="";
    private String userPass="";
    private String smtpHost="";
    private String pop3Host="";
    private String userName="";
    private String file="";
    private String fileName="";
    
    
    public SendFrame(String userAddr, String userPass, String smtpHost, String pop3Host) {
        initComponents();
        this.userAddr=userAddr;
        this.userPass=userPass;
        this.smtpHost=smtpHost;
        this.pop3Host=pop3Host;
        String []temp=userAddr.split("@", 2);
        this.userName=temp[0];
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDataArea = new javax.swing.JTextArea();
        txtToAddr = new javax.swing.JTextField();
        txtFilename = new javax.swing.JTextField();
        txtSubject = new javax.swing.JTextField();
        btnSendMail = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("编辑邮件");

        jLabel1.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel1.setText("收件地址：");

        jLabel2.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel2.setText("附件名称：");

        jLabel3.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel3.setText("邮件主题：");

        btnFile.setText("添加附件");
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });

        txtDataArea.setColumns(20);
        txtDataArea.setLineWrap(true);
        txtDataArea.setRows(5);
        jScrollPane1.setViewportView(txtDataArea);

        txtSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubjectActionPerformed(evt);
            }
        });

        btnSendMail.setText("发送");
        btnSendMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMailActionPerformed(evt);
            }
        });

        btnReset.setText("取消");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btnSendMail)
                        .addGap(41, 41, 41)
                        .addComponent(btnReset))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSubject))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtFilename, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnFile))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtToAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtToAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFile)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtFilename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSendMail)
                    .addComponent(btnReset)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubjectActionPerformed

    private void btnSendMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMailActionPerformed
        // TODO add your handling code here:
        //发送邮件
       SendMail sm = new SendMail(smtpHost,userAddr,this.txtToAddr.getText(),
               file,fileName,userName,userPass,
               this.txtSubject.getText(),this.txtDataArea.getText());
       int r=sm.Send();
       if(r==1)
           JOptionPane.showMessageDialog(null, "邮件发送成功", 
                   "提示", JOptionPane.OK_CANCEL_OPTION);
       else
           JOptionPane.showMessageDialog(null, "邮件发送失败", 
                   "错误提示", JOptionPane.ERROR_MESSAGE);
       this.dispose();
       new MainFrame(userAddr,userPass,smtpHost,pop3Host).setVisible(true);
    }//GEN-LAST:event_btnSendMailActionPerformed

    private void btnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("选择发送文件");
        fileChooser.setApproveButtonText("确定");
        int choice=fileChooser.showOpenDialog(this); //显示对话框
        if (choice==JFileChooser.APPROVE_OPTION) { //点击选择按钮
            //获取文件对象
            File f=fileChooser.getSelectedFile();
            fileName=fileChooser.getName(f);
            this.txtFilename.setText(fileName);
            file=f.getAbsolutePath();
       }
    }//GEN-LAST:event_btnFileActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new MainFrame(userAddr,userPass,smtpHost,pop3Host).setVisible(true);
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFile;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSendMail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDataArea;
    private javax.swing.JTextField txtFilename;
    private javax.swing.JTextField txtSubject;
    private javax.swing.JTextField txtToAddr;
    // End of variables declaration//GEN-END:variables
}
