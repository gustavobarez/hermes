package br.com.gustavobarez.hermes.Order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PatchMapping
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> updateOrder(@RequestBody UpdateOrderRequestDTO request,
            @RequestParam Long id) {
        var order = service.updateOrder(request, id);
        var response = new ApiResponseDTO<>(order, "update-order");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> deleteOrder(@RequestParam Long id) {
        var order = service.deleteOrder(id);
        var response = new ApiResponseDTO<>(order, "delete-order");
        return ResponseEntity.ok(response);
    }

}
