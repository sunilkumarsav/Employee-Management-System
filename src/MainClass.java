import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainClass 
{
	private JFrame frame=new JFrame("Insert Employee Form");
	private JButton[] buttons=new JButton[5];
	public MainClass()
	{
		frame.setSize(600,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		addButtons();
		frame.setVisible(true); 
	}
	private void addButtons()
	{
		int y=50;
		Font font=new Font("arial",Font.PLAIN,20);
		String[] str= {"Insert", "Delete", "Update","Search","Show all"};
		MenuListener listener = new MenuListener();
		for(int i=0; i<buttons.length;i++)
		{
			buttons[i]=new JButton(str[i]+" Record");
			buttons[i].setBounds(200, y, 200, 33);
			buttons[i].setFont(font);
			frame.add(buttons[i]);
			buttons[i].addActionListener(listener);
			y+=80;
		}
	}
	// ab insert button pe click krne pe uska vska vala form open ho jaye,/ iske liye uska constructor run krna chahiye. kisi class ka constructor us class ka object bnane pe run krta h 
	
	private class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) 
		{	
			JButton bb=(JButton)evt.getSource();
			
			if(bb==buttons[0])// Insert button
			{
				new insertRecord2();
			}
			else if(bb==buttons[1])// Delete button
			{
				new DeleteRecord();
			}
			else if(bb==buttons[2])// Update button
			{
				new UpdateRecord();
			}
			else if(bb==buttons[3])// Search button
			{
				new SearchRecord();
			}
			else if(bb==buttons[4])// Show All button
			{
				new ShowAllRecord();
			}
		}	
	}
	public static void main(String[] args)
	{
		new MainClass();
	}
}
