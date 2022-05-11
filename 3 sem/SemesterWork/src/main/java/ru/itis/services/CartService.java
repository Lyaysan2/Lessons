package ru.itis.services;

import ru.itis.dto.CartDto;
import ru.itis.dto.CommentsDto;
import ru.itis.dto.UserDto;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Optional<User> getUserByEmail (String email);
    List<CartDto> getByUserId(Integer userId);
    void addToCart(CartDto cartDto);
    void deleteFromCart(Integer id);
}
