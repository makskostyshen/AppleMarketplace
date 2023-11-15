package com.example.applemarketplace.data.good.stock;

import com.example.applemarketplace.data.good.GoodEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "good_stock")
public class GoodStockEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @OneToOne
    private GoodEntity good;

    @Column(name = "quantity")
    private Long quantity;

    public void updateQuantity(final Long change) {
        quantity += change;
    }
}
