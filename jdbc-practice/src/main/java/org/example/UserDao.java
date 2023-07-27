package org.example;
import java.sql.*;

public class UserDao {

    //TO-BE
//    public void create(User user) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstat = null;
//
//        try {
//            conn = ConnectionManager.getConnection();
//            String sql = "INSERT INTO USERS VALUES(?,?,?,?)";
//
//            pstat = conn.prepareStatement(sql);
//            pstat.setString(1,user.getUserId());
//            pstat.setString(2,user.getPassword());
//            pstat.setString(3,user.getName());
//            pstat.setString(4,user.getEmail());
//
//            pstat.executeUpdate();
//        }finally {
//            if(pstat != null){
//                pstat.close();
//            }
//
//            if(conn != null){
//                conn.close();
//            }
//        }
//    }

    //AS-IS
    public void create(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES(?,?,?,?)";
        jdbcTemplate.executeUpdate(user, sql, pstmt -> {
                pstmt.setString(1,user.getUserId());
                pstmt.setString(2,user.getPassword());
                pstmt.setString(3,user.getName());
                pstmt.setString(4,user.getEmail());
        });
    }

//    public User findByUserId(String userId) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstat = null;
//        ResultSet result = null;
//
//        try {
//            conn = ConnectionManager.getConnection();
//            String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
//
//            pstat = conn.prepareStatement(sql);
//            pstat.setString(1, userId);
//
//            result = pstat.executeQuery();
//
//            User user = null;
//            if (result.next()) {
//                user = new User(
//                        result.getString("userId"),
//                        result.getString("password"),
//                        result.getString("name"),
//                        result.getString("email")
//                );
//            }
//            return user;
//        } finally {
//
//            if (result != null) {
//                result.close();
//            }
//
//            if (pstat != null) {
//                pstat.close();
//            }
//
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
        return (User) jdbcTemplate.executeQuery(sql, pstmt -> {
                                                                pstmt.setString(1, userId);
                                                            }
                                                            , resultSet -> {
                                                                return new User(
                                                                        resultSet.getString("userId"),
                                                                        resultSet.getString("password"),
                                                                        resultSet.getString("name"),
                                                                        resultSet.getString("email")
                                                                    );
                                                            });
    }
}
