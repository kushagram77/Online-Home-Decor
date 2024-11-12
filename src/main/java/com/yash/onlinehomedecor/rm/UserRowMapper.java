package com.yash.onlinehomedecor.rm;
/**
 *
 * @author Kushagra Mishra
 */
import com.yash.onlinehomedecor.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user=new User();
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("name"));
        user.setId(resultSet.getInt("id"));
        return user;
    }
}
