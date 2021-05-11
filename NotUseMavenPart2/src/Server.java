import java.sql.*;

public class Server {
    private String DB_URL = "jdbc:mysql://localhost:3306/BTL";
    private String USER_NAME = "root";
    private String PASSWORD = "Hungvq2001";

    public static Connection connect;
    public static Statement stmt;
    public static ResultSet rs;
    public static String list;

    public Connection getConnect(String dbURL, String userName,
                                 String password) {
        Connection conn = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

    public void getConnection() {
        try {
            // connnect to database 'testdb'
            connect = getConnect(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            stmt = connect.createStatement();
            // get data from table 'student'
            rs = stmt.executeQuery("select * from customers");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void fixToString(String oldString, String newString){
        String sql = "update customers set customerName = ? where customerName = ?;";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement(sql);
            ps.setString(1, newString);
            ps.setString(2, oldString);
            int n =  ps.executeUpdate();
            System.out.println(n);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    };
    public static void fixToInt(String oldInt, int newInt){
        String sql = "update customers set phone = ? where customerName = ?;";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement(sql);
            ps.setInt(1, newInt);
            ps.setString(2, oldInt);
            int n =  ps.executeUpdate();
            System.out.println(n);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    };
    public static void fixToDate(Date oldDate, Date newDate){
        String sql = "update customers set customerName = ? where customerName = ?;";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement(sql);
            ps.setDate(1, newDate);
            ps.setDate(2, oldDate);
            int n =  ps.executeUpdate();
            System.out.println(n);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    };

    public static void fixToOldIntConsume(int oldInt, int newInt){
        String sql = "update consume set oldIndex = ? where oldIndex = ?;";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement(sql);
            ps.setInt(1, newInt);
            ps.setInt(2, oldInt);
            int n =  ps.executeUpdate();
            System.out.println(n);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    };
    public static void fixToNewIntConsume(int oldInt, int newInt){
        String sql = "update consume set newIndex = ? where newIndex = ?;";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement(sql);
            ps.setInt(1, newInt);
            ps.setInt(2, oldInt);
            int n =  ps.executeUpdate();
            System.out.println(n);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    };
    public static void fixToStringConsume(String oldString, String newString, int m){
        String sql = "update consume set month = ? where month = ? and customerNumber = ?";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement(sql);
            ps.setString(1, newString);
            ps.setString(2, oldString);
            ps.setInt(3, m);
            int n =  ps.executeUpdate();
            System.out.println(n);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    };

    public static boolean delete(String s) throws SQLException {
        String sql = "delete from customers where customerName = ?";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement(sql);
            ps.setString(1, s);
            return ps.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int s) throws SQLException {
        String sql = "delete from customers where customerNumber = ?";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement(sql);
            ps.setInt(1, s);
            System.out.println(ps.execute());
            return ps.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static void showData(String s) throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM CUSTOMERS WHERE customerName = '" + s + "'");
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            break;
        }
    }

    public static boolean addCustomer(int s1, String s2, String s3, String s4, int s5, String s6, Date s7, Date s8, String s9) {
        String sql = "insert into customers(customerNumber, customerName, idCardNumber, gender, phone, address, birthDay, startDay, type)" +
                    " value(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement(sql);
            ps.setInt(1, s1);
            ps.setString(2, s2);
            ps.setString(3, s3);
            ps.setString(4, s4);
            ps.setInt(5, s5);
            ps.setString(6, s6);
            ps.setDate(7, s7);
            ps.setDate(8, s8);
            ps.setString(9, s9);

            return ps.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static void addNewNumberElectric(int id, int number) {
        int oldNumber = 0;
        try {
            rs = stmt.executeQuery("select * from consume");
            while (rs.next()) {
                if(id == rs.getInt(1)) {
                    oldNumber = rs.getInt(4);
                    System.out.println(oldNumber);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "update consume set newIndex = ?, oldIndex = ? where customerNumber = ?;";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement(sql);
            ps.setInt(1, number);
            ps.setInt(2, oldNumber);
            ps.setInt(3, id);
            int n =  ps.executeUpdate();
            System.out.println(n);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void Close() throws SQLException {
        connect.close();
    }

    public static void main(String args[]) throws SQLException {
        Server c = new Server();
        c.getConnection();
        //addNewNumberElectric(26020604, 701);
        //showData("nguyen duc hung");
        System.out.println(java.time.LocalTime.now());
        String s = String.valueOf(java.time.LocalDateTime.now());
        System.out.println(s);
    }
}
