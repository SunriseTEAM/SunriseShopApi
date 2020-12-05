package me.sunrise.shopapi.repository;

import me.sunrise.shopapi.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
