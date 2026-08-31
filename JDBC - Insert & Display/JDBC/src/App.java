import java.sql.*; 
 
public class App {
    public static void main(String args[]) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college",
                "root",
                "sql@1234");
            System.out.println(con);
            System.out.println("connection created");
            PreparedStatement ps = con.prepareStatement(
            "INSERT INTO student VALUES (?, ?, ?)"
        );

        ps.setInt(1, 102);
        ps.setString(2, "Uday");
        ps.setString(3, "AI");

        int rows = ps.executeUpdate();

        System.out.println(rows + " record inserted");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM student");
        while (rs.next()) {
           int id = rs.getInt("id");
           String name = rs.getString("name1");
           String branch = rs.getString("branch");
           System.out.println(id + " " + name + " " + branch);
        }

        con.close();
        }
    } 

