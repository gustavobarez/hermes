package br.com.gustavobarez.hermes.Route;

import java.time.LocalDateTime;

import br.com.gustavobarez.hermes.Delivery.Delivery;
import br.com.gustavobarez.hermes.Order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "routes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private Order order;

    @NotNull
    @OneToOne
    private Delivery delivery;

    @Column(name = "attributed_At")
    private LocalDateTime attributedAt;

    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @Column(name = "updated_At")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_At")
    private LocalDateTime deletedAt;

}
