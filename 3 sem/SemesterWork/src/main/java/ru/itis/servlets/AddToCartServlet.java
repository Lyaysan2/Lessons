package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.BookDto;
import ru.itis.dto.CartDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Book;
import ru.itis.models.User;
import ru.itis.services.CartService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/cart")
public class AddToCartServlet extends HttpServlet {

    private CartService cartService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.cartService = springContext.getBean(CartService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = (String) request.getSession().getAttribute("email");
        Optional<User> userOptional = cartService.getUserByEmail(email);

        Book book = (Book) request.getSession().getAttribute("book");

        CartDto form = CartDto.builder()
                .user(UserDto.from(userOptional.get()))
                .book(BookDto.from(book))
                .build();

        cartService.addToCart(form);
    }
}
