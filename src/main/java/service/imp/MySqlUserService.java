package service.imp;

import exception.NoSuchBlogException;
import model.Blog;
import model.User;
import jdbc.MySqlConnector;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySqlUserService implements UserService {

    private static Connection connection;

    static {
        try {
            connection = MySqlConnector.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createUser(User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO web.users (id, first_name, last_name, age) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setInt(4, user.getAge());
            statement.execute();
        }
    }

    @Override
    //TODO: Implement
    public List<User> getAllUsers() throws SQLException {

        return null;
    }

    @Override
    //TODO: Implement
    public User getUserById(int id) throws SQLException, NoSuchBlogException {
        return null;
    }
}
