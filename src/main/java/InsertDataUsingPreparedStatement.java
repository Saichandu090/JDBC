import java.sql.*;

public class InsertDataUsingPreparedStatement
{
    public static void main(String[] args) throws SQLException {

        Driver driver=new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);

        try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_db?user=root&password=Saichandu@090");
            PreparedStatement st=conn.prepareStatement("INSERT INTO INVENTORY VALUES(?,?,?,?);"))
        {
            st.setInt(1,4);
            st.setString(2,"Desktop");
            st.setInt(3,6999);
            st.setInt(4,27);

            int rs=st.executeUpdate();
            if(rs!=0)
            {
                System.out.println(rs+" row(s) inserted!!");
            }
            else
                System.out.println("No rows Inserted!!");
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
