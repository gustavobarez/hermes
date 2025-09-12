package br.com.gustavobarez.hermes.Order;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        Order order = Order.builder()
                .description(request.description())
                .originAddress(request.originAddress())
                .deliveryAddress(request.deliveryAddress())
                .status(request.status())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(order);

        OrderResponseDTO response = new OrderResponseDTO(order);

        return response;
    }

    public Optional<Order> findById(Long orderId) {
        return repository.findById(orderId);
    }

}
