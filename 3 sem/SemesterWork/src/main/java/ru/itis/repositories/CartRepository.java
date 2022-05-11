package ru.itis.repositories;

import ru.itis.models.Cart;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart>{
    List<Cart> findByUserId(Integer userId);
}
