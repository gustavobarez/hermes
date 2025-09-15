package br.com.gustavobarez.hermes.Shipment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api/v1/shipments")
@Tag(name = "Shipment", description = "Shipment management endpoints")
public class ShipmentController {

    private final ShipmentService service;

    public ShipmentController(ShipmentService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create Shipment", description = "Responsible for creating a new shipment, typically from an order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Shipment created successfully", content = {
                    @Content(schema = @Schema(implementation = ApiResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<ApiResponseDTO<ShipmentResponseDTO>> createShipment(
            @Valid @RequestBody ShipmentRequestDTO request) {
        var shipment = service.createShipment(request);
        var response = new ApiResponseDTO<>(shipment, "create-shipment");
        return ResponseEntity.ok(response);
    }

}
