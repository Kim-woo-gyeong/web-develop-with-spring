package org.example;
import java.sql.*;

public class UserDao {

    public void create(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstat = null;

        try {
            conn = getConnection();
            String sql = "INSERT INTO USERS VALUES(?,?,?,?)";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1,user.getUserId());
            pstat.setString(2,user.getPassword());
            pstat.setString(3,user.getName());
            pstat.setString(4,user.getEmail());

            pstat.executeUpdate();
        }finally {
            if(pstat != null){
                pstat.close();
            }

            if(conn != null){
                conn.close();
            }
        }
    }

    public User findByUserId(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet result = null;

        try {
            conn = getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1,userId);

            result = pstat.executeQuery();

            User user = null;
            if(result.next()){
                user = new User(
                        result.getString("userId"),
                        result.getString("password"),
                        result.getString("name"),
                        result.getString("email")
                );
            }
            return user;
        }finally {

            if(result != null){
                result.close();
            }

            if(pstat != null){
                pstat.close();
            }

            if(conn != null){
                conn.close();
            }
        }
    }

    public Connection getConnection(){
        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySql;DB_CLOSE_DELAY=-1";
        String id = "sa";
        String pw = "";

        try{
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(url,id,pw);
        }catch (Exception e){
            return null;
        }
    }


}
