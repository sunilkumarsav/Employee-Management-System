import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteRecord 
{
	private static JFrame frame;
	private static JLabel label;
	private static JTextField textbox;
	private static JButton button;
	private static JButton button1;
	private static JPanel panel;
	private static JLabel[] heading;
	private static JLabel[] data;
	private static PreparedStatement ps ;// jb ye code run kiya hoga to ps nam ka ak variable bna hoga aur usme null dal diya jayega./ jaise hi value dal ke submit mara ActionListener ka try  vala code run krega  
	private static PreparedStatement ps1 ;// delete ke liye
	
	static
	{
		frame=new JFrame("Delete Employee Form");
		label=new JLabel("Enter Employee id");
		textbox= new JTextField();
		button=new JButton("Delete Record");
		button1=new JButton(" Confirm Delete");
		panel= new JPanel();
		heading = new JLabel[3];// jo bhi declared krna h sb private me krna h. ye encapsulation ka guideline h
		data= new JLabel[3];
		
			frame.setSize(600,500);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			frame.setLayout(null);
			addComponents();
			addPanel();
			ps=DbConnection.psselect;// jb ye code run krega to ps me jo object bnanae ka code likha h uska reference  aa jata  ./ ab null pointer exception nhi aayega
			ps1=DbConnection.psdelete;
			frame.setVisible(true);
	}
	private static void addComponents()
	{
		String[] str = {"id", "name", "dept", "salary"};
		int y=50;
		Font font=new Font("arial", Font.PLAIN,22);
		Font font1=new Font("arial", Font.PLAIN,19);
		label.setBounds(50,y,250,30);
		label.setFont(font);
		frame.add(label);
		textbox.setBounds(300,y,250,33);
		textbox.setFont(font1);
		frame.add(textbox);
		
		button.setBounds(50,120,500,35);
		button.setFont(font1);
		frame.add(button);
		button.addActionListener(new ShowListener());// button ke sath listener register kr rkha h
	    
		button1.setBounds(50,430,500,35);
		button1.setFont(font1);
		frame.add(button1);
		button1.setVisible(false);
		button1.addActionListener(new DeleteListener());
	}
	private static void addPanel()
	{
		panel.setBounds(50,200,500,230);
		panel.setBackground(Color.red);
		frame.add(panel);
		panel.setVisible(false);
		panel.setBorder(BorderFactory.createLineBorder(Color.orange,3));
		addResult();
	}
	private static void addResult()
	{
		panel.setLayout(new GridLayout(3,2,100,0));
		Font font= new Font("Lucida Calligraphy",Font.BOLD,20);
		Font font1=new Font("arial",Font.PLAIN,20);
		String[] str = {"name","dept","salary"};
		for(int i=0;i<3;i++)
		{
			heading[i]=new JLabel("Employee "+str[i]);
			panel.add(heading[i]);
			heading[i].setFont(font);
			heading[i].setForeground(Color.white);
			data[i]=new JLabel();
			panel.add(data[i]);
			data[i].setFont(font1);
			data[i].setForeground(Color.white);
		}
	}
	static class ShowListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				//yha pe method call hua h setString./ ps ke pas object ka reference hai hi nhi./isiliye null pointer exception aa jayega
				ps.setString(1,textbox.getText());// query complete ho gyi ab query ko execute krne ke liye bhejna hoga
				// bhejne ke liye executeQuery call krna hoga. resultset object ka object ka reference dega
				ResultSet rst=ps.executeQuery();
				if(rst.next())
				{
					panel.setVisible(true);// iske click krne pe panel ka setVisible property true kr diya jayega
					data[0].setText(rst.getString(2));//2 islye h ki 1 pe id h aur hme nam chahiye
					data[1].setText(rst.getString(3));// 3 - department ke liye
					data[2].setText(rst.getString(4));// 4- salary ke liye
					button1.setVisible(true);
				}
				else
				{
					panel.setVisible(false);
					button1.setVisible(true);
					JOptionPane.showMessageDialog(frame, "Record does not exist");
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
		
	}
	static class DeleteListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) 
		{
			try
			{
				ps1.setString(1,textbox.getText());
				ps1.executeUpdate();// executeUdate ka method call kr diya gya
				panel.setVisible(false);
				button1.setVisible(false);
				JOptionPane.showMessageDialog(frame, "Record has been deleted");
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
		
	}
}
