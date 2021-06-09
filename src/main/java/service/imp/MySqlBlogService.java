package service.imp;

import exception.DuplicateBlogException;
import exception.NoSuchBlogException;
import jdbc.MySqlConnector;
import model.Blog;
import service.BlogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlBlogService implements BlogService {

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
    public List<Blog> getAll() throws SQLException {
        List<Blog> blogs = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM web.blogs")) {
            while (result.next()) {
                blogs.add(new Blog(result.getInt("id"), result.getString("name"), result.getInt("user_id")));
            }
            return blogs;
        }
    }

    @Override
    public Blog getBlogById(int id) throws SQLException, NoSuchBlogException {
        ResultSet result = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM web.blogs WHERE id = ?")) {
            statement.setInt(1, id);
            result = statement.executeQuery();
            if (result.next()) {
                return new Blog(result.getInt("id"), result.getString("name"), result.getInt("user_id"));
            } else throw new NoSuchBlogException("No blog with id : " + id);
        } finally {
            result.close();
        }
    }

    @Override
    public void createBlog(Blog blog) throws SQLException, DuplicateBlogException {
        if (isExists(blog.getId())) {
            throw new DuplicateBlogException("Blog with id : " + blog.getId() + " already exists!");
        }

        //TODO:
        //Зробити перевірку чи є юзер з ідентифікатором blog.getUserId()
        System.out.println("Creating blog with id : " + blog.getId());
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO web.blogs (id, name, user_id) VALUES (?, ?, ?)")) {
            statement.setInt(1, blog.getId());
            statement.setString(2, blog.getName());
            statement.setInt(3, blog.getUserId());
            statement.execute();
        }
    }


    private boolean isExists(int blogId) throws SQLException {
        boolean flag = false;
        for (Blog blog : getAll()) {
            flag = blog.getId() == blogId;
        }
        return flag;
    }
}
