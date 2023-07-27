package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreParedStatementSetter {
    void setter(PreparedStatement pstmt) throws SQLException;
}
