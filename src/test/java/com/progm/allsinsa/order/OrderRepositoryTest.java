package com.progm.allsinsa.order;

import com.progm.allsinsa.order.domain.Order;
import com.progm.allsinsa.order.domain.OrderProduct;
import com.progm.allsinsa.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@EnableJpaAuditing
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository repository;

    String orderNumber;

    @BeforeEach
    @Test
    void setup() {
        OrderProduct orderProduct1 = new OrderProduct("바지",
                3000,
                2,
                "사이즈 : S",
                "https://www.google.com/url?sa=i&url=http%3A%2F%2Fwww.sportsseoul.com%2Fnews%2Fread%2F988300&psig=AOvVaw33h5sXl1K-OPcKgT7BmBGK&ust=1635391936561000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPj-tfLT6fMCFQAAAAAdAAAAABAD",
                2L);

        OrderProduct orderProduct2 = new OrderProduct("신발",
                10000,
                1,
                "사이즈 : M",
                "https://www.google.com/url?sa=i&url=http%3A%2F%2Fwww.sportsseoul.com%2Fnews%2Fread%2F988300&psig=AOvVaw33h5sXl1K-OPcKgT7BmBGK&ust=1635391936561000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPj-tfLT6fMCFQAAAAAdAAAAABAD",
                3L);


        Order order = new Order(1L,
                "김현준",
                "01026846867",
                "경기도 수원시 영통구 영통동",
                "부재시 경비실에 맡겨주세요",
                16000);
        order.addOrderProduct(orderProduct1);
        order.addOrderProduct(orderProduct2);

        repository.save(order);

        orderNumber = order.getOrderNumber();
    }

    @Test
    @Transactional
    void 주문번호로조회_테스트() {
        Optional<Order> byOrderNumber = repository.findByOrderNumber(orderNumber);
        System.out.println(byOrderNumber.get());
    }
}
