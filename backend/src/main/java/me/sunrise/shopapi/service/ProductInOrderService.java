package me.sunrise.shopapi.service;

import me.sunrise.shopapi.entity.ProductInOrder;
import me.sunrise.shopapi.entity.User;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder findOne(String itemId, User user);
}
