package ru.sultanyarov.stockexchangedashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserStock.class)
@Table(name = "user_stock")
public class UserStock implements Serializable {
    @Id
    @Column(name = "user_id")
    private UUID userId;
    @Id
    @Column(name = "stock_id")
    private UUID stockId;
}
