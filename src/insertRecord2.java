import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;

import javax.swing.*;

public class insertRecord2 
{
	private static JFrame frame;
	private static JLabel[] label;
	private static JTextField[] textbox;
	private static JButton button;
	private static PreparedStatement ps, ps1;
	static
	{
		frame =new JFrame("Insert Record Form");
		label= new JLabel[4];
		textbox = new JTextField[4];
		button=new JButton("Save Record");
		frame.setSize(600,500);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		addComponents();
		ps=DbConnection.psinsert;// ps nam ka local variable bna rkha h.agr ps nam ka local variable use nhi krte/ psisert prepared statement ka object h jisme insert vale preparedStaement ke object ka reference h 
		ps1=DbConnection.psselect;
		frame.setVisible(true);
	}
	public static void addComponents()
	{
		String[] str = {"id", "name", "dept", "salary"};
		int y=50;
		Font font=new Font("arial", Font.PLAIN,22);
		Font font1=new Font("arial", Font.PLAIN,19);
		for(int i=0;i<label.length; i++)
		{
			label[i] = new JLabel("Enter employee "+ str[i]);
			label[i].setFont(font);
			label[i].setForeground(Color.blue);
			label[i].setBounds(50,y,250,30);
			frame.add(label[i]);
			textbox[i]=new JTextField();
			textbox[i].setBounds(300,y,250,33);
			textbox[i].setFont(font1);
			textbox[i].setForeground(Color.red);
			frame.add(textbox[i]);
			y+=70;
		}
		button.setBounds(50,350,500,35);
		button.setFont(font1);
		frame.add(button);
		button.addActionListener(new SaveListener());
	}
	
	static class SaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) 
		{
			String eid=textbox[0].getText();
			String name=textbox[1].getText();
			String dept=textbox[2].getText();
			String salary=textbox[3].getText();
			if(eid.equals("")|| name.equals("")|| dept.equals("")|| salary.equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Every value must be entered");
				return;
			}
			try
			{	
				ps1.setString(1, eid);
				ResultSet rst=ps1.executeQuery();
				if(rst.next())
				{
					JOptionPane.showMessageDialog(frame, "intered id has already been inserted");
					return;

				}
				ps.setString(1, eid);
				ps.setString(2, name);
				ps.setString(3, dept);
				ps.setString(4, salary);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(frame, "Record has been inserted successfully");	
				for(JTextField tb:textbox)
				{
					tb.setText("");
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}	
		}
	}
}
