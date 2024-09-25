import java.sql.*;

public class ExecuteSimpleQuery
{

    public static void main(String[] args) throws SQLException
    {
        Driver driver=new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);

        try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_employeedb","root","Saichandu@090");
            PreparedStatement st=conn.prepareStatement("select * from employee;");)
        {
            ResultSet rs=st.executeQuery();
            System.out.println("EmployeeId\t\tEmployeeName\t\tEmployeeEmail\t\tEmployeeSalary\t\tEmployeeDesignation");
            while(rs.next())
            {
                System.out.print(rs.getInt(1)+"\t\t\t\t");
                System.out.print(rs.getString(2)+"\t\t\t\t");
                System.out.print(rs.getString(3)+"\t\t\t\t");
                System.out.print(rs.getInt(4)+"\t\t\t\t");
                System.out.print(rs.getString(5)+"\t\t\t\t");
                System.out.println();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
