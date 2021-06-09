package service;

import exception.NoSuchBlogException;
import model.Blog;
import model.User;

import java.sql.SQLException;

public interface UserService {

    void createUser(User user) throws SQLException;

    Blog getBlogById(int id) throws SQLException, NoSuchBlogException;

}
