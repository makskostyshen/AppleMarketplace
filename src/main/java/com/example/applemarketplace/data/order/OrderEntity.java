package com.example.applemarketplace.data.order;

import com.example.applemarketplace.data.good.purchase.GoodPurchaseEntity;
import com.example.applemarketplace.data.user.ClientProfileEntity;
import com.example.applemarketplace.service.model.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "\"order\"")
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<GoodPurchaseEntity> goodPurchases = new ArrayList<>();

    @Column(name = "bill")
    private BigDecimal bill;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "payed_on")
    private Instant payedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private ClientProfileEntity client;

    public OrderEntity(
            final BigDecimal bill,
            final List<GoodPurchaseEntity> purchases,
            Instant createdOn) {

        this.bill = bill;
        this.createdOn = createdOn;
        this.goodPurchases = new ArrayList<>();
        this.status = OrderStatus.UNPAID;

        purchases.forEach(purchase -> {
            this.goodPurchases.add(purchase);
            purchase.setOrder(this);
        });
    }
}
