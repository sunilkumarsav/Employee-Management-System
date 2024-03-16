import java.sql.*;
public class DbConnection 
{
	public static PreparedStatement psinsert,psselect,psdelete,psupdate;
	public static Statement stselect;
	static //isko kahte h static block.iski speciality yah hoti hai ki class ke load hone pe run krta h aur class ki speciality h ki pure project life cycle me ak bat load hota h  
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc12?createDatabaseIfNotExist=true","root","mysql");
			psinsert = cn.prepareStatement("insert into employee values(?,?,?,?)");
			psselect = cn.prepareStatement("select * from employee where eid=?");
			psdelete = cn.prepareStatement("delete from employee where eid=?");
			psupdate = cn.prepareStatement("update employee set name=?,department=?,salary=? where eid=?");
		    stselect=cn.createStatement();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
