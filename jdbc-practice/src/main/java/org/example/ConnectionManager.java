package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

// 커넥션 관련 정보.
public class ConnectionManager {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySql;DB_CLOSE_DELAY=-1";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PW = "";
    public static final int MAX_POOL_SIZE = 40;
    private static final DataSource ds;

    static {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername(DB_USERNAME);
        hikariDataSource.setPassword(DB_PW);

        // DB커넥션 풀에서 꺼내옴. DB커넥션 풀로 HikariCP를 사용한다.
        // DB커넥션 풀을 크게 하면 메모리 사용량이 많다.
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

        ds = hikariDataSource;
        // 커넥션을 하나만 가지도록 설정해준 부분.
    }


    public static Connection getConnection(){
        try{
            return ds.getConnection();
        }catch (SQLException e){
            // 영향을 끼치지 않기 위해 try catch 로 에러를 잡음.
            throw new IllegalArgumentException();
        }
        // 데이터소스 로부터 데이터 커넥션을 받아옴.
    }

    public static DataSource getDataSource(){
        return ds;
    }
}
