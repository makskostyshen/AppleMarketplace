package com.example.applemarketplace.data.good.purchase;

import com.example.applemarketplace.data.good.GoodEntity;
import com.example.applemarketplace.data.order.OrderEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "good_purchase")
public class GoodPurchaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "good_id")
    @ToString.Exclude
    private GoodEntity good;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private OrderEntity order;

    @Column(name = "count")
    private Integer count;
}
