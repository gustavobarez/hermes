package br.com.gustavobarez.hermes.Order;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO request) {
        Order order = Order.builder()
                .description(request.description())
                .originAddress(request.originAddress())
                .deliveryAddress(request.deliveryAddress())
                .status(request.status())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(order);

        CreateOrderResponseDTO response = new CreateOrderResponseDTO(order);

        return response;
    }

}
