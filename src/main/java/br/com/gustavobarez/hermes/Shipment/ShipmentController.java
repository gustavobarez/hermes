package br.com.gustavobarez.hermes.Shipment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavobarez.hermes.utils.ApiResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {
    
    private final ShipmentService service;

    public ShipmentController(ShipmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<ShipmentResponseDTO>> createShipment(@Valid @RequestBody ShipmentRequestDTO request) {
        var shipment = service.createShipment(request);
        var response = new ApiResponseDTO<>(shipment, "create-shipment");
        return ResponseEntity.ok(response);
    }

}
