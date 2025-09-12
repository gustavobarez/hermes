package br.com.gustavobarez.hermes.Courier;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavobarez.hermes.utils.ApiResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/couriers")
public class CourierController {

    private final CourierService service;

    public CourierController(CourierService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<CourierResponseDTO>> createCourier(
            @Valid @RequestBody CourierRequestDTO request) {
        var courier = service.createCourier(request);
        var response = new ApiResponseDTO<>(courier, "create-courier");
        return ResponseEntity.ok(response);
    }

}
