package me.sunrise.shopapi.service;

import me.sunrise.shopapi.entity.Cart;
import me.sunrise.shopapi.entity.ProductInOrder;
import me.sunrise.shopapi.entity.User;

import java.util.Collection;

public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
