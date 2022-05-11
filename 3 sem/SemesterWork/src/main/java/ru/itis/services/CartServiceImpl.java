package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.CartDto;
import ru.itis.dto.CommentsDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Book;
import ru.itis.models.Cart;
import ru.itis.models.Comments;
import ru.itis.models.User;
import ru.itis.repositories.CartRepository;
import ru.itis.repositories.CommentsRepository;
import ru.itis.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UsersRepository usersRepository) {
        this.cartRepository = cartRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.of(usersRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<CartDto> getByUserId(Integer userId) {
        return cartRepository.findByUserId(userId).stream()
                .map(CartDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void addToCart(CartDto cartDto) {
        Cart bookToCart = Cart.builder()
                .user(User.builder()
                        .id(cartDto.getUser().getId())
                        .firstName(cartDto.getUser().getFirstName())
                        .lastName(cartDto.getUser().getLastName())
                        .email(cartDto.getUser().getEmail())
                        .avatarId(cartDto.getUser().getAvatarId())
                        .build())
                .book(Book.builder()
                        .id(cartDto.getBook().getId())
                        .name(cartDto.getBook().getName())
                        .author(cartDto.getBook().getAuthor())
                        .sellerId(cartDto.getBook().getSellerId())
                        .build())
                .build();
        cartRepository.save(bookToCart);

    }

    @Override
    public void deleteFromCart(Integer id) {
        cartRepository.delete(id);
    }
}
