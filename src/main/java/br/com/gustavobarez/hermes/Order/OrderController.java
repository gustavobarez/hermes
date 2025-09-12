package br.com.gustavobarez.hermes.Order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavobarez.hermes.utils.ApiResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> createOrder(@Valid @RequestBody OrderRequestDTO request) {
        var order = service.createOrder(request);
        var response = new ApiResponseDTO<>(order, "create-order");
        return ResponseEntity.ok(response);
    }

}
