package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// JdbcTemplate 는 라이브러리이다.
public class JdbcTemplate {
    public void executeUpdate(User user, String sql, PreParedStatementSetter pss) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();

            // PreparedStatement를 외부 파라미터로 받으려면 connection 자체도 외부에서 받아야 하는 문제가 발생.
            // 그래서 setting 부분을 외부에서 받는 것으로 수정해야함.
            pstmt = conn.prepareStatement(sql);
            pss.setter(pstmt);


            pstmt.executeUpdate();
        }finally {
            if(pstmt != null){
                pstmt.close();
            }

            if(conn != null){
                conn.close();
            }
        }
    }

    public Object executeQuery(String sql, PreParedStatementSetter pss, RowMapper rowMapper) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        try {
            conn = ConnectionManager.getConnection();

            pstmt = conn.prepareStatement(sql);
            pss.setter(pstmt);

            result = pstmt.executeQuery();

            User user = null;
            if (result.next()) {
                return rowMapper.map(result);
            }
            return null;
        } finally {

            if (result != null) {
                result.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }
}
