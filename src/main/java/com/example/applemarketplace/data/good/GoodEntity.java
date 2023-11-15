package com.example.applemarketplace.data.good;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "good")
public class GoodEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_on")
    private Instant createdOn;
}
