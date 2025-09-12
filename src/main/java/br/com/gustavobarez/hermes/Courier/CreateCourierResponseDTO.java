package br.com.gustavobarez.hermes.Courier;

public record CreateCourierResponseDTO(Long id, String name) {
    public CreateCourierResponseDTO(Courier courier) {
        this(
                courier.getId(),
                courier.getName());
    }
}