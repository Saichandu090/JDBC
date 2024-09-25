import java.sql.*;

public class HandleTransactions
{
    public static void main(String[] args) throws Exception {

        Driver driver=new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);

        int fromAcc=1;
        int toAcc=2;
        int amount=2000;
        Connection conn=null;
        Savepoint savepoint1=null;
        try
        {
            //Step-1 : Establish the connection
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_db","root","Saichandu@090");

            //Step-2 : Disable autocommit
            conn.setAutoCommit(false);
            PreparedStatement st=conn.prepareStatement("select * from bank where accountno=?;");
            st.setInt(1,fromAcc);
            ResultSet rs=st.executeQuery();
            System.out.println("AccountNo\tName\t\tAge\t\tBalance");
            while(rs.next())
            {
                System.out.print(rs.getInt(1)+"\t\t\t");
                System.out.print(rs.getString(2)+"\t");
                System.out.print(rs.getInt(3)+"\t\t");
                System.out.print(rs.getInt(4));
                System.out.println();
            }

            savepoint1=conn.setSavepoint();
            //step-3 : Deduct the balance
            PreparedStatement se=conn.prepareStatement("UPDATE bank set balance=balance-? where accountno=?;");
            se.setInt(1,amount);
            se.setInt(2,fromAcc);

            int res=se.executeUpdate();
            if(res==0)
            {
                throw new SQLException("Amount not deducted from "+fromAcc);
            }

            //Step-4 : Add the balance to account
            PreparedStatement s1=conn.prepareStatement("update bank set balance=balance+? where accountno=?;");
            s1.setInt(1,amount);
            s1.setInt(2,toAcc);

            int r1=s1.executeUpdate();
            if(r1==0)
            {
                throw new SQLException(amount+" not added to "+toAcc);
            }

            conn.commit();
            System.out.println("Transaction successfully completed!!");
            System.out.println("============================");

            PreparedStatement st1=conn.prepareStatement("select * from bank;");
            ResultSet rs1 =st1.executeQuery();
            System.out.println("AccountNo\tName\t\tAge\t\tBalance");
            while(rs1.next())
            {
                System.out.print(rs1.getInt(1)+"\t\t\t");
                System.out.print(rs1.getString(2)+"\t");
                System.out.print(rs1.getInt(3)+"\t\t");
                System.out.print(rs1.getInt(4));
                System.out.println();
            }

        }
        catch (SQLException e)
        {
            if(conn!=null)
            {
                conn.setAutoCommit(false);
                System.out.println("Transaction Unsuccessfull!!,rolling back changes");
                conn.rollback(savepoint1);
                conn.commit();
            }
        }

        finally {
            if(conn!=null)
            {
                conn.close();
            }
        }
    }
}
