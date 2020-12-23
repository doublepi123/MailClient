package cn.edu.ldu;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import sun.misc.BASE64Encoder;
/**
 *
 * 邮件发送类
 *
 * @author yk
 */
public class SendMail {
    private String smtpHost = "";  //smtp服务器
    private String fromAddr = "";  //发件人地址
    private String toAddr = "";    //收件人地址
    private String File = ""; //附件地址
    private String fileName = ""; //附件名称
    private String userName = "";  //用户名
    private String userPass = "";   //密码
    private String subject = ""; //邮件标题
    private String text="";//邮件正文
    public SendMail(String smtpHost, String fromAddr, String toAddr, String file,
			String fileName, String userName, String userPass, String subject,
			String text) {
		this.smtpHost = smtpHost;
		this.fromAddr = fromAddr;
		this.toAddr = toAddr;
		this.File = file;
		this.fileName = fileName;
		this.userName = userName;
		this.userPass = userPass;
		this.subject = subject;
		this.text = text;
	}
    public int Send() {
        Properties props = new Properties();
        //设置发送邮件的邮件服务器的属性
        props.put("mail.smtp.host", smtpHost);
        //需要经过授权，也就是有户名和密码的校验，这样才能通过验证
        props.put("mail.smtp.auth", "true");
        //用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);//控制台打印交互日志
        //用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            //加载发件人地址
            message.setFrom(new InternetAddress(fromAddr));
            //加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));
            //加载标题
            message.setSubject(subject);
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            //设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(text);
            multipart.addBodyPart(contentPart);
            //将附件字符串转入数组
            int n = File.length();
            String fileAddr[] = new String[50];
            int cnt = 0;
            int last = 0;
            for(int i = 0 ; i < n ; i++){
                if(File.charAt(i) == ';') {
                    fileAddr[cnt++] = File.substring(last, i);
                    last = i + 1;
                }
            }
            //添加附件
            if(!(File.equalsIgnoreCase("") || File.equalsIgnoreCase(null))){
                for(int i = 0 ; i < cnt ; i++) {
                    BodyPart messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(fileAddr[i]);
//                    System.out.println(fileAddr[i]+"HERE");
                    messageBodyPart.setDataHandler(new DataHandler(source));//添加附件的内容
                    //添加附件的标题
                    BASE64Encoder encoder = new BASE64Encoder();
                    messageBodyPart.setFileName("=?GBK?B?" + encoder.encode(fileName.getBytes()) + "?=");
                    multipart.addBodyPart(messageBodyPart);
                }
            }
            message.setContent(multipart);//将multipart对象放到message中
            message.saveChanges();//保存邮件
            //发送邮件
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, userName,userPass);//连接服务器的邮箱
            //把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
