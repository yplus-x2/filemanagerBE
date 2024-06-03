package com.progetto.filemanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userinfo")
public class UserInfoEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Lob
    @Column(name = "profilepic")
    private byte[] profilepic;

    @Column(name = "mimetype")
    private String profilepicMimeType;

    @OneToOne(mappedBy = "id_userinfo")
    @JsonBackReference
    private UserEntity userEntity;

}
