package com.example.applemarketplace.data.user;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "manager_profile")
public class ManagerProfileEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccountEntity account;
}
