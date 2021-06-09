import model.Blog;
import exception.DuplicateBlogException;
import exception.NoSuchBlogException;
import service.BlogService;
import service.UserService;
import service.imp.MySqlBlogService;
import service.imp.MySqlUserService;

import java.sql.SQLException;

public class Main {

    private static BlogService blogService = new MySqlBlogService();
    private static UserService userService = new MySqlUserService();

    public static void main(String[] args) throws SQLException, NoSuchBlogException, DuplicateBlogException {
    }
}
