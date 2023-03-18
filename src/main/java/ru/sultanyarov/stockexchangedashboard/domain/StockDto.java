package ru.sultanyarov.stockexchangedashboard.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "stock")
@NoArgsConstructor
public class StockDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private UUID id;
    private String name;
    private String ticker;
    private String currency;
    private String logo;
    @Transient
    private Double currentPrice;
    @Transient
    private Double change;
    @ManyToMany(mappedBy = "stocks")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> users;
}