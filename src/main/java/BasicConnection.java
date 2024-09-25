import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasicConnection
{
    public static void main(String[] args) throws SQLException
    {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        try(Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db","root","Saichandu@090"))
        {
            if(conn!=null)
                System.out.println("Connection is Successfull");
            else
                System.out.println("Connection not established");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
