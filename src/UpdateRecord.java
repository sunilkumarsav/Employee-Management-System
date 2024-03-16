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

public class UpdateRecord 
{
	 private static JFrame frame;
	 private static JLabel label;
	 private static JTextField textbox;
	 private static JButton button;
	 private static JButton update;
	 private static PreparedStatement ps1,ps2;
	 private static JPanel panel;
   	static JLabel[] heading;
    static JTextField[] data;
	    static
	    {
	    	frame=new JFrame("Update Employee Form");
	    	label =new JLabel("Enter employee id");
	    	textbox=new JTextField(); 
	    	button=new JButton("Show Record");
	    	update=new JButton("Update Record");
	    	panel=new JPanel();
	    	heading=new JLabel[3];
	    	data =new JTextField[3];
	    	
		        frame.setSize(600,500);
		        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setResizable(false);
		        frame.setLocationRelativeTo(null);
		        frame.setLayout(null);
		        addComponents();
		        addPanel();
		        ps1=DbConnection.psselect;
		        ps2=DbConnection.psupdate;
		        frame.setVisible(true);

		}
	private static void addComponents()
	{
		 String[] str= {"id","name","dept","salary"};
	        Font font=new Font("arial",Font.PLAIN,22);
	        Font font1=new Font("arial",Font.PLAIN,19);
	        label.setBounds(50,50,250,30);
	        label.setFont(font);
	        frame.add(label);
	        textbox.setBounds(300,50,250,30);
	        textbox.setFont(font1);
	        frame.add(textbox);
	        
	        button.setBounds(50,120,500,35);
	        button.setFont(font1);
	        frame.add(button);
	        button.addActionListener(new ShowListener());
	        
	        update.setBounds(50,425,500,35);
	        update.setFont(font1);
	        frame.add(update);
	        update.setVisible(false);
	        
	        update.addActionListener(new UpdateListener());

	}
	private static void addPanel()
	{
		panel.setBounds(50,200,500,230);
		panel.setBackground(Color.cyan);
		frame.add(panel);
		panel.setVisible(false);
		addResult();
	}
	private static void addResult()
	{
		    panel.setLayout(new GridLayout(3,2,0,50));
	        Font font=new Font("Lucida Calligraphy",Font.BOLD,20);
	        Font font1=new Font("arial",Font.PLAIN,20);
	        String[] str= {"name","dept","salary"};
	        for(int i=0;i<3;i++)
	        {
	            heading[i]=new JLabel("Edit employee "+str[i]);
	            panel.add(heading[i]);
	            heading[i].setFont(font);
	            data[i]=new JTextField();
	            panel.add(data[i]);
	            data[i].setFont(font1);
	            data[i].setForeground(Color.blue);
	        }
	}
	static class ShowListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
        {
            int eid=Integer.parseInt(textbox.getText());
            try  
            {
                ps1.setInt(1,eid);
                ResultSet rst=ps1.executeQuery();
                if(rst.next())
                {
                    panel.setVisible(true);
                    update.setVisible(true);
                    data[0].setText(rst.getString(2));
                    data[1].setText(rst.getString(3));
                    data[2].setText(rst.getString(4));
                }
                else
                {
                    update.setVisible(false);
                    panel.setVisible(false);
                    JOptionPane.showMessageDialog(frame,"Record not found");
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }		
	}
	static class UpdateListener implements ActionListener
	    {
	        public void actionPerformed(ActionEvent evt)
	        {
	            try
	            {
	                ps2.setString(1,data[0].getText());
	                ps2.setString(2,data[1].getText());
	                ps2.setString(3,data[2].getText());
	                ps2.setString(4,textbox.getText());
	                ps2.executeUpdate();
	                JOptionPane.showMessageDialog(frame,"Record has been updated");
	                panel.setVisible(false);
	                update.setVisible(false);
	            }
	            catch(Exception ex)
	            {
	                System.out.println(ex);
	            }
	        }
	    }
}
