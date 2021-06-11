package service.imp;

import exception.NoSuchBlogException;
import exception.NoSuchUserException;
import model.Blog;
import model.User;
import jdbc.MySqlConnector;
import service.UserService;

import java.sql.*;
import java.util.ArrayList;
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
        List<User> users = new ArrayList<>();
        ResultSet result = null;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM web.users")){
            while (result.next()){
                users.add(new User(result.getInt("id"), result.getString("first_name"), result.getString("last_name"), result.getInt("age")));
            }
        }
        return users;
    }

    @Override
    //TODO: Implement
    public User getUserById(int id) throws SQLException, NoSuchUserException {
        ResultSet result = null;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM web.users WHERE id = ?")){
            statement.setInt(1, id);
            result = statement.executeQuery();
            if (result.next()) {
                return new User(result.getInt("id"), result.getString("first_name"), result.getString("last_name"), result.getInt("age"));
            }else throw new NoSuchUserException("No user with id: " + id);
        }finally {
            result.close();
        }
    }

}
