package cn.bluethink.jframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.bluethink.entity.UserInfo;
import cn.bluethink.util.RestUtil;

public class LoginJframe {
	private JTextField nameText, passwordText;
	private final JFrame jFrame = new JFrame();
	public LoginJframe(){				
		SetLoginJframe();
	}
	void SetLoginJframe() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setBounds(((int)dimension.getWidth() - 320) / 2, ((int)dimension.getHeight() -520) / 2, 340,240);
		jFrame.setResizable(false);
		jFrame.setLayout(null);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel label= new JLabel("登陆");
		label.setFont(new java.awt.Font("宋体",3,20));
		label.setBounds(145,10, 200, 30);
		jFrame.add(label);	
		
		JButton registerButton = new JButton("注册");
		registerButton.setFont(new java.awt.Font("宋体",   1,   14));
		registerButton.setBounds(220,12, 80, 25);
		registerButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				new RegisterJframe();
				jFrame.dispose();
			}
		});
		jFrame.add(registerButton);
		
		JLabel nameLabel= new JLabel("姓名：");
		nameLabel.setFont(new java.awt.Font("宋体",1,18));
		nameLabel.setBounds(40,60, 200, 30);
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
		
		JLabel passwordLabel= new JLabel("密码：");
		passwordLabel.setFont(new java.awt.Font("宋体",1,18));
		passwordLabel.setBounds(40,100, 200, 30);
		jFrame.add(passwordLabel);
		
		passwordText=new JTextField();
		passwordText.setFont(new java.awt.Font("宋体",1,16));
		passwordText.setBounds(100,102, 160, 30);
		passwordText.setForeground(Color.blue);
		passwordText.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(passwordText.getText().length() >= 16) {
					e.consume();
				}
			}
		});
		jFrame.add(passwordText);	
		
		
		JButton confirmButton = new JButton("确认");
		confirmButton.setFont(new java.awt.Font("宋体",   1,   14));
		confirmButton.setBounds(50, 160, 90, 25);
		
		
		JButton backButton= new JButton("返回");
		backButton.setFont(new java.awt.Font("宋体",   1,   14));
		backButton.setBounds(190,160, 90, 25);
		
		confirmButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {		
				if(nameText.getText().length()==0 ) {
					JOptionPane.showMessageDialog(jFrame, "信息不完整！","提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (passwordText.getText().length() < 8) {
						JOptionPane.showMessageDialog(jFrame, "密码请填写8到16位！","提示", JOptionPane.INFORMATION_MESSAGE);
						return;						
					}
					RestUtil.paramList.put("username", nameText.getText());
					RestUtil.paramList.put("password", passwordText.getText());
					UserInfo userInfo = RestUtil.userLogin();
					if (userInfo != null) {
						clearText();
						jFrame.dispose();
					}		
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
		passwordText.setText("");
	}
}
