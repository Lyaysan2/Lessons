package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.AddBookForm;
import ru.itis.dto.UserDto;
import ru.itis.models.FileInfo;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.BookService;
import ru.itis.services.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Optional;

@MultipartConfig
@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {

    private BookService bookService;
    private FilesService filesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.bookService = applicationContext.getBean(BookService.class);
        this.filesService = applicationContext.getBean(FilesService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/templates/addBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = (String) request.getSession().getAttribute("email");
        UserDto userDto = bookService.getUserByEmail(email);

        Part part = request.getPart("file");

        Integer id = 0;

        if(part.getSize() != 0){

            FileInfo file = filesService.upload(part.getSubmittedFileName(), part.getSize(), part.getContentType(),
                    part.getInputStream());
            id = file.getId();
        }

        try{
            AddBookForm addBookForm = AddBookForm.builder()
                    .name(request.getParameter("name"))
                    .author(request.getParameter("author"))
                    .year(Integer.valueOf(request.getParameter("year")))
                    .description(request.getParameter("description"))
                    .price(Integer.valueOf(request.getParameter("price")))
                    .sellerId(userDto.getId())
                    .imageId(id)
                    .build();

            this.bookService.addBook(addBookForm);
        } catch (NumberFormatException e){
            response.sendRedirect("/addBook?error");
            return;
        }

        response.sendRedirect("/myBooks");
    }
}
