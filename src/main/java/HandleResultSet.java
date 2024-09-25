import java.sql.*;

public class HandleResultSet
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db?user=root&password=Saichandu@090");
            PreparedStatement st=conn.prepareStatement("SELECT * FROM School;"))
        {
            ResultSet rs=st.executeQuery();
            System.out.println("StudentId\tStudentName\tStudentGrade");
            while(rs.next())
            {
                System.out.print(rs.getInt(1)+"\t\t\t");
                System.out.print(rs.getString(2)+"\t\t\t");
                System.out.print(rs.getString(3)+"\t\t\t");
                System.out.println();
            }

            System.out.println("====================================");
            Statement se=conn.createStatement();
            int ee=se.executeUpdate("UPDATE SCHOOL SET GRADE='B' WHERE STUDENTID=1;");
            if(ee!=0)
            {
                System.out.println(ee+" rows updated!!");
                ResultSet resultSet =st.executeQuery();
                System.out.println("StudentId\tStudentName\tStudentGrade");
                while(resultSet.next())
                {
                    System.out.print(resultSet.getInt(1)+"\t\t\t");
                    System.out.print(resultSet.getString(2)+"\t\t\t");
                    System.out.print(resultSet.getString(3)+"\t\t\t");
                    System.out.println();
                }
            }
            else
                System.out.println("Not updated!!");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
