package br.com.gustavobarez.hermes.Courier;

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
@RequestMapping("/api/v1/couriers")
@Tag(name = "Courier", description = "Courier management endpoints")
public class CourierController {

    private final CourierService service;

    public CourierController(CourierService service) {
        this.service = service;
    }
    @PostMapping
    @Operation(summary = "Create Courier", description = "Responsible for creating a new courier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Courier created successfully", content = {
                    @Content(schema = @Schema(implementation = ApiResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<ApiResponseDTO<CourierResponseDTO>> createCourier(
            @Valid @RequestBody CourierRequestDTO request) {
        var courier = service.createCourier(request);
        var response = new ApiResponseDTO<>(courier, "create-courier");
        return ResponseEntity.ok(response);
    }


}
