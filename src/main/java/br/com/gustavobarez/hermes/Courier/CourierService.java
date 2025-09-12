package br.com.gustavobarez.hermes.Courier;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class CourierService {

    private final CourierRepository repository;

    public CourierService(CourierRepository repository) {
        this.repository = repository;
    }

    public CreateCourierResponseDTO createCourier(CreateCourierRequestDTO request) {

        Courier courier = Courier.builder()
                .name(request.name())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(courier);

        CreateCourierResponseDTO response = new CreateCourierResponseDTO(courier);

        return response;

    }

}
