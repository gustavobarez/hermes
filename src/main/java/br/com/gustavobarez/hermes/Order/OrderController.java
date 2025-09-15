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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Order", description = "Order management endpoints")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create Order", description = "Responsible for creating a new delivery order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order created successfully", content = {
                    @Content(schema = @Schema(implementation = ApiResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> createOrder(@Valid @RequestBody OrderRequestDTO request) {
        var order = service.createOrder(request);
        var response = new ApiResponseDTO<>(order, "create-order");
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    @Operation(summary = "Update Order", description = "Responsible for updating an existing order by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order updated successfully", content = {
                    @Content(schema = @Schema(implementation = ApiResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "404", description = "Order not found with the given ID"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> updateOrder(@RequestBody UpdateOrderRequestDTO request,
            @RequestParam Long id) {
        var order = service.updateOrder(request, id);
        var response = new ApiResponseDTO<>(order, "update-order");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Operation(summary = "Delete Order", description = "Responsible for deleting an existing order by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order deleted successfully", content = {
                    @Content(schema = @Schema(implementation = ApiResponseDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Order not found with the given ID"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> deleteOrder(@RequestParam Long id) {
        var order = service.deleteOrder(id);
        var response = new ApiResponseDTO<>(order, "delete-order");
        return ResponseEntity.ok(response);
    }

}
