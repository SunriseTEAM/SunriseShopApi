package me.sunrise.service;

import me.sunrise.entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderMain> findAll(Pageable pageable);


    Page<OrderMain> findstatus1(Pageable pageable);

    Page<OrderMain> findstatus2(Pageable pageable);

    Page<OrderMain> findstatus3(Pageable pageable);



    abstract Page<OrderMain> findByStatus(Integer status, Pageable pageable);

    Page<OrderMain> findByBuyerEmail(String email, Pageable pageable);

    Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable);

    OrderMain findOne(Long orderId);


    OrderMain finish(Long orderId);

    OrderMain approved(Long orderId);

    OrderMain cancel(Long orderId);

//    Page<OrderMain> findByStatus1(Integer orderStatus);
}
