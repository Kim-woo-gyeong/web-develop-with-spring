package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

public class UserDaoTest {
    @BeforeEach
    public void  setUp(){
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();

        resourceDatabasePopulator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(resourceDatabasePopulator, ConnectionManager.getDataSource());
    }

    @Test
    public void createTest() throws SQLException {
        UserDao userDao = new UserDao();
        userDao.create(new User("wizard", "password", "name", "email")); // 저장
        User user = userDao.findByUserId("hong"); // 조회

        Assertions.assertThat(user).isEqualTo(new User("wizard", "password", "name", "email"));
    }
}
