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

    public OrderResponseDTO updateOrder(UpdateOrderRequestDTO request, Long orderId) {
        var order = repository.findById(orderId);

        if (request.description() == null && request.originAddress() == null && request.deliveryAddress() == null) {
            throw new IllegalArgumentException("All fields cannot be empty or null");
        }

        if (request.description() != null) {
            order.get().setDescription(request.description());
        }

        if (request.originAddress() != null) {
            order.get().setOriginAddress(request.originAddress());
        }

        if (request.deliveryAddress() != null) {
            order.get().setDeliveryAddress(request.deliveryAddress());
        }

        if (request.status() != null) {
            order.get().setStatus(request.status());
        }

        order.get().setUpdatedAt(LocalDateTime.now());

        repository.save(order.get());

        OrderResponseDTO response = new OrderResponseDTO(order.get());

        return response;
    }

    public Optional<Order> findById(Long orderId) {
        return repository.findById(orderId);
    }

}
