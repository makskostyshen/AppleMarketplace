package com.example.applemarketplace.data.order;

import com.example.applemarketplace.data.good.purchase.GoodPurchaseEntity;
import com.example.applemarketplace.service.model.OrderStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ord")
public class OrderEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @OneToMany(
            mappedBy = "ord",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<GoodPurchaseEntity> goodPurchases = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;


    @Column(name = "created_on")
    private Instant createdOn;
}
