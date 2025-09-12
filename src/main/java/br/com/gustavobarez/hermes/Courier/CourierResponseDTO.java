package br.com.gustavobarez.hermes.Courier;

public record CourierResponseDTO(Long id, String name) {
    public CourierResponseDTO(Courier courier) {
        this(
                courier.getId(),
                courier.getName());
    }
}