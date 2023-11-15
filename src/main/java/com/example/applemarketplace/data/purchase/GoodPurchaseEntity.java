package com.example.applemarketplace.data.purchase;

import com.example.applemarketplace.data.good.GoodEntity;
import com.example.applemarketplace.data.order.OrderEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "good_purchase")
public class GoodPurchaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private GoodEntity good;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity ord;
}
