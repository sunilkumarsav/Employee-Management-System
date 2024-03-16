import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//import java.awt.*;
import java.sql.*;
public class ShowAllRecord 
{
	private static JFrame frame;
	private static Statement st;// yha pe prepared ki jarurt mhi h
	private static JTable table;// record ko table me dikhana h. to yha JTable nam ki ak class h jo ki swing package me h.
    private static JScrollPane pane;// Table me charo taraf scrollbar chahiye. table blank/light waight  rha h.kyoki hme kabhi kabhi aisi table chahiye jisme scrollbar na aave.To java ne kha ki hm apne swing component ko light weight kr rhe h.kyoki agr scrollbar ka feature de de aur aap use use na kre to necessary memory me load ho jayega.to memory me space jyada occupy krega. isiliye unhine ScrollPane na ki alg se class bna diya.
    private static DefaultTableModel model;
    static
    {
    	frame=new JFrame("Showing All Records"); // frame bna rha hu
    	model =new DefaultTableModel();//table ko data DefaultTableModel class ke object data milega./ to 3 different classes h Table UI ke liye ho gya, Scrollbar table me scrollbar dalne ke liye, aur difault table model hamara program database se record nikal ke isi ke object me dalega aur iske object se table lega aur user ko show krega ye frame ban gya
            frame.setSize(600,500);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            st=DbConnection.stselect;
            showResultSet();
            frame.setVisible(true);
        
    }

    private static void showResultSet()
    {
        table=new JTable(model);// yha pe table ka object bnaya, aur table ko data model ke object se milna chahiye,to iske constructor me model ke object ka reference pass kr diya.
        //table.setFont(new Font("arial",0,20));
        pane=new JScrollPane(table);// ab hme table ko frame me add krna hoga, lekin frame me table mt add kro.frame me aap add kro ScrollPane/ye ScrollPane ka object bna ke iske constructor me table pass kr diya . ab table chli gyi ScrollPane me aur ScrollPane ko frame me add kr diya.
        frame.add(pane);//ScrollPane ko frame me add kr diya. to frame me ScroolPane aa gya aur  ScrollPane me table
        
        //Ab hmko model me heading dalna h (hmko table 4 heading chahiye)
        // to yha model ka method call kiya addColumn.Employee id,Employee name,Employee dept,Employee salary ye 4 heading ho gyi
        model.addColumn("Employee id");
        model.addColumn("Employee name");
        model.addColumn("Employee dept");
        model.addColumn("Employee salary");
        
        // iske bad isme hmko data dalna h iske liye select query execute krayenge
        try 
        {
            ResultSet rst=st.executeQuery("select * from employee");//iske bad isme hmko data dalna h iske liye select query execute krayenge.to sara record ResultSet ke object me aa jayega 
            while(rst.next())//ab loop chalayenge ,jitne record honge utni bar loop chalega,agr 10 recod honge to loop 10 bar chalega./ aur next() method phli bar cursoer ko phle record pe le jayega aur return krega true. again dusre pe bhi retun krega true. and so on
             {
                Object[] data= {rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4)};// ab yha pe java ne bola aap apna record ak object type ke array me dal dijiye. to jb phli bar loop chlega to object type ka ak array bnega aur us array me phle record ki charo value aa jayegi/ aur usko model me add kr diya / fir dusri bar bhi yhi hoga, tisri bar bhi yhi hoga,......jitna row hoga unti bar chalega / 
                model.addRow(data);// is trh se data ko tabular form me dikha dega./ mdel kehta h hmko jo row me data dijiyega object type ka array dijiyega
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
