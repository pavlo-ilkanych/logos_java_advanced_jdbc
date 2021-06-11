package service;

import exception.NoSuchBlogException;
import exception.NoSuchUserException;
import model.Blog;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void createUser(User user) throws SQLException;

    User getUserById(int id) throws SQLException, NoSuchBlogException, NoSuchUserException;

    List<User> getAllUsers() throws SQLException;

}
