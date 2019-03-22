package cn.bluethink.jframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.bluethink.dto.UserDTO;
import cn.bluethink.entity.UserInfo;
import cn.bluethink.util.DataUtil;
import cn.bluethink.util.JsonUtil;
import cn.bluethink.util.RestUtil;


public class RegisterJframe {
	private JTextField nameText, sexText, ageText, phoneText, emailText, passwordText, authText;
	private final JFrame jFrame = new JFrame();
	public RegisterJframe(){				//构造函数
		SetRegisterJframe();
	}
	void SetRegisterJframe() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setBounds(((int)dimension.getWidth() - 320) / 2, ((int)dimension.getHeight() -520) / 2, 340,460);
		jFrame.setResizable(false);
		jFrame.setLayout(null);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				jFrame.dispose();
			}
		});
		JLabel label= new JLabel("注册");
		label.setFont(new java.awt.Font("宋体",3,20));
		label.setBounds(145,10, 200, 30);
		jFrame.add(label);	
		
		JLabel nameLabel= new JLabel("姓名：");
		nameLabel.setFont(new java.awt.Font("宋体",1,18));
		nameLabel.setBounds(40,60, 200, 30);
		nameLabel.setForeground(Color.red);
		jFrame.add(nameLabel);	
		 
		nameText=new JTextField();
		nameText.setFont(new java.awt.Font("宋体",1,16));
		nameText.setBounds(100,62, 160, 30);
		nameText.setForeground(Color.blue);	
		nameText.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(nameText.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		jFrame.add(nameText);	
		
		JLabel sexLabel= new JLabel("性别：");
		sexLabel.setFont(new java.awt.Font("宋体",1,18));
		sexLabel.setBounds(40,100, 200, 30);
		sexLabel.setForeground(Color.red);
		jFrame.add(sexLabel);
		
		sexText=new JTextField();
		sexText.setFont(new java.awt.Font("宋体",1,16));
		sexText.setBounds(100,102, 160, 30);
		sexText.setForeground(Color.blue);;
		sexText.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(sexText.getText().length() >= 1) {
					e.consume();
				}
			}
		});
		jFrame.add(sexText);	
		
		JLabel ageLabel= new JLabel("年龄：");
		ageLabel.setFont(new java.awt.Font("宋体",1,18));
		ageLabel.setBounds(40,140, 200, 30);
		ageLabel.setForeground(Color.red);
		jFrame.add(ageLabel);
		
		ageText=new JTextField();
		ageText.setFont(new java.awt.Font("宋体",1,16));
		ageText.setBounds(100,142, 160, 30);
		ageText.setForeground(Color.blue);
		ageText.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();				
				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
					if (ageText.getText().length() >= 3) {
						e.consume();
					}
				}else{
					e.consume();
				}
			}
		});
		jFrame.add(ageText);	
		
		JLabel phoneLabel= new JLabel("手机：");
		phoneLabel.setFont(new java.awt.Font("宋体",1,18));
		phoneLabel.setBounds(40,180, 200, 30);
		jFrame.add(phoneLabel);
		
		phoneText=new JTextField();
		phoneText.setFont(new java.awt.Font("宋体",1,16));
		phoneText.setBounds(100,182, 160, 30);
		phoneText.setForeground(Color.blue);
		phoneText.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();				
				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
					if (phoneText.getText().length() >= 11) {
						e.consume();
					}
				}else{
					e.consume(); //关键，屏蔽掉非法输入
				}
			}
		});
		jFrame.add(phoneText);	
		
		
		JLabel emailLabel= new JLabel("邮箱：");
		emailLabel.setFont(new java.awt.Font("宋体",1,18));
		emailLabel.setBounds(40,220, 200, 30);
		jFrame.add(emailLabel);
		
		emailText=new JTextField();
		emailText.setFont(new java.awt.Font("宋体",1,16));
		emailText.setBounds(100,222, 160, 30);
		emailText.setForeground(Color.blue);
		emailText.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(emailText.getText().length() >= 21) {
					e.consume();
				}
			}
		});
		jFrame.add(emailText);	
		
		JLabel passwordLabel= new JLabel("密码：");
		passwordLabel.setFont(new java.awt.Font("宋体",1,18));
		passwordLabel.setBounds(40,260, 200, 30);
		passwordLabel.setForeground(Color.red);
		jFrame.add(passwordLabel);
		
		passwordText=new JTextField();
		passwordText.setFont(new java.awt.Font("宋体",1,16));
		passwordText.setBounds(100,262, 160, 30);
		passwordText.setForeground(Color.blue);
		passwordText.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(passwordText.getText().length() >= 16) {
					e.consume();
				}
			}
		});
		jFrame.add(passwordText);	
		
		JLabel authLabel= new JLabel("权限：");
		authLabel.setFont(new java.awt.Font("宋体",1,18));
		authLabel.setBounds(40,300, 200, 30);
		authLabel.setForeground(Color.gray);
		jFrame.add(authLabel);
		
		authText=new JTextField();
		authText.setFont(new java.awt.Font("宋体",1,16));
		authText.setBounds(100,302, 160, 30);
		authText.setForeground(Color.gray);
		authText.setText("普通用户");
		authText.setEditable(false);
		jFrame.add(authText);	
		
		JLabel tipLabel= new JLabel("提示：红色为必填内容");
		tipLabel.setFont(new java.awt.Font("宋体",1,18));
		tipLabel.setBounds(40,340, 200, 30);
		tipLabel.setForeground(Color.blue);
		jFrame.add(tipLabel);
		
		JButton confirmButton = new JButton("确认");
		confirmButton.setFont(new java.awt.Font("宋体",   1,   14));
		confirmButton.setBounds(50, 390, 90, 25);
		
		
		JButton backButton= new JButton("返回");
		backButton.setFont(new java.awt.Font("宋体",   1,   14));
		backButton.setBounds(190,390, 90, 25);
		
		confirmButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {		
				if(nameText.getText().length()==0
						||sexText.getText().length()==0
						||ageText.getText().length()==0
						||passwordText.getText().length()==0) {
					JOptionPane.showMessageDialog(jFrame, "信息不完整！","提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String sex = sexText.getText();
					if (!"男".equals(sex) && !"女".equals(sex)) {
						JOptionPane.showMessageDialog(jFrame, "性别填写错误！","提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if (Integer.parseInt(ageText.getText()) > 200) {
						JOptionPane.showMessageDialog(jFrame, "年龄填写错误！","提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if (emailText.getText().length() > 0) {
						if (!emailText.getText().contains("@")) {
							JOptionPane.showMessageDialog(jFrame, "邮箱填写错误！","提示", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}
					if (passwordText.getText().length() < 8) {
						JOptionPane.showMessageDialog(jFrame, "密码请填写8到16位！","提示", JOptionPane.INFORMATION_MESSAGE);
						return;
						
					}
					UserDTO userDTO = new UserDTO();
					userDTO.setUserName(nameText.getText());
					userDTO.setUserSex("男".equals(sexText.getText())? 1:2);
					userDTO.setUserAge(Integer.parseInt(ageText.getText()));
					userDTO.setUserPhone(phoneText.getText());
					userDTO.setUserEmail(emailText.getText());
					userDTO.setUserPassword(passwordText.getText());
					userDTO.setUserAuth("common");
					String jsonBody = JsonUtil.bean2Json(userDTO);
					UserInfo userInfo = RestUtil.userAdd(jsonBody);
					if (userInfo != null) {
						DataUtil.currentUser = userInfo;
					}
					System.out.println("userInfo: " + userInfo);
					JOptionPane.showMessageDialog(jFrame, "注册成功！","提示", JOptionPane.INFORMATION_MESSAGE);
					clearText();
					jFrame.dispose();
				}
			}
		});
		backButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		jFrame.add(confirmButton);
		jFrame.add(backButton);
		jFrame.setLocationRelativeTo(null);  
		jFrame.setResizable(false);
       
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		jFrame.setVisible(true); 
	}

	public JFrame getjFrame() {
		return jFrame;
	}
	public void clearText() {					
		nameText.setText("");
		sexText.setText("");
		ageText.setText("");
		phoneText.setText("");
		emailText.setText("");
		passwordText.setText("");
	}
}
