import java.sql.*;
import java.util.*;
class ASSnew {
    public static void Select(int acc_no) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/bank";
        String user = "root";
        String pass = "MySQL";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,user,pass);
        Statement s=con.createStatement();
        ResultSet rs=s.executeQuery("select balance from customerdetial where Acc_no=" + (""+acc_no));
        while(rs.next()) {
            System.out.println(rs.getInt("balance"));
        }
    }
    public static void updatePhone(int acc_no,int Phone_no,int pin_no) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/bank";
        String user = "root";
        String pass = "MySQL";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,user,pass);
        Statement s=con.createStatement();
        PreparedStatement ps = con.prepareStatement("update bank.customerdetial set Phone_no =?,pin_no=? where acc_no=?;");
        ps.setInt(2, acc_no);
        ps.setInt(1,Phone_no);
        ps.setInt(3,pin_no);
        ps.executeUpdate();
        Show();

    }
    public static void Show()throws ClassNotFoundException, SQLException{
        String url="jdbc:mysql://localhost:3306/bank";
        String user ="root";
        String pass = "MySQL";
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("driver loaded");
        Connection con = DriverManager.getConnection(url,user,pass);
        System.out.println("connection");
        Statement st= con.createStatement();

        ResultSet rs =st.executeQuery("select * from customerdetial");
        while(rs.next()){
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+ rs.getString(4)+" "+rs.getString(5)+" "+ rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(8)+" "+rs.getString(9));
        }

    }
    public static void insert(int acc_no,String acc_name,int pin_no,String pan_no,int balance,String address,String acc_type,String bank_name,int phone_no)throws ClassNotFoundException, SQLException{
        String url="jdbc:mysql://localhost:3306/bank";
        String user ="root";
        String pass = "MySQL";
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("driver loaded");
        Connection con = DriverManager.getConnection(url,user,pass);
        System.out.println("connection");
        Statement st= con.createStatement();
        int row = st.executeUpdate("insert into customerdetial values("+acc_no+",'"+acc_name+"',"+pin_no+",'"+pan_no+"',"+balance+",'"+address+"','"+acc_type+"','"+bank_name+"',' "+phone_no+" ')");
        if(row>0){
            System.out.println("insert is complete");
        }
        else {
            System.out.println("error");
        }
    }
}
public class ASS{
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc= new Scanner(System.in);
        while(true){
        System.out.println("1> See the balance");
        System.out.println("2> update phone & pin ");
        System.out.println("3> insert all element");
        int option=sc.nextInt();
        if(option==1){
            System.out.println("enter the acc_no");
            int acc_no=sc.nextInt();
            ASSnew.Select(acc_no);
        } else if (option==2) {
            System.out.println("enter the acc_no");
            int acc_no=sc.nextInt();
            System.out.println("enter the phone no");
            int Phone_no=sc.nextInt();
            System.out.println("enter the pin no");
            int pin_no=sc.nextInt();
            ASSnew.updatePhone(acc_no,Phone_no,pin_no);

        } else if (option==3) {
            System.out.println("enter the acc_no");
            int acc_no=sc.nextInt();
            System.out.println("enter the phone no");
            int phone_no=sc.nextInt();
            System.out.println("enter the pin no");
            int pin_no=sc.nextInt();
            System.out.println("enter the acc name");
            sc.nextLine();
            String acc_name= sc.nextLine();
            System.out.println("enter the pan no");
            String pan_no= sc.nextLine();
            System.out.println("enter the balance");
            int balance = sc.nextInt();
            System.out.println("enter the Address");
            sc.nextLine();
            String address=sc.nextLine();
            System.out.println("enter the Acc_type");
            String acc_type= sc.nextLine();
            System.out.println("enter the Bank");
            String bank_name=sc.nextLine();

            ASSnew.insert(acc_no,acc_name, pin_no,pan_no,balance,address,acc_type, bank_name, phone_no);

        }
        else{
            break;
        }

    }
}
}