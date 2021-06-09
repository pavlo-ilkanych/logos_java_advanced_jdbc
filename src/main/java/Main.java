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

/*        System.out.println(" ----------- GET all ---------------- ");
        blogService.getAll().forEach(System.out::println);

        System.out.println(" ------------- GET by ID ----------------");
        System.out.println(blogService.getBlogById(45));*/

      /*  System.out.println(" ---------- CREATE User --------------");
        userService.createUser(new User(6, "New"));*/

        System.out.println(" ---------- CREATE Blog with User --------------");
        blogService.createBlog(new Blog(8, "Window 8"));

        System.out.println(" ------------- GET by ID ----------------");
        System.out.println(blogService.getBlogById(7));




    }
}
