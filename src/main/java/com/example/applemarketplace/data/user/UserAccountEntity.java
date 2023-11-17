package com.example.applemarketplace.data.user;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_account")
public class UserAccountEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "account")
    @ToString.Exclude
    private ClientProfileEntity clientProfile;

    @OneToOne(mappedBy = "account")
    @ToString.Exclude
    private ManagerProfileEntity managerProfile;
}
