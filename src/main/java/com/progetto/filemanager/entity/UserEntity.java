package com.progetto.filemanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "user")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

//    @Column(name = "tokenjwt")
//    private String tokenjwt;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userEntity")
    @JsonBackReference
    private List<FileEntity> fileEntities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_userinfo", referencedColumnName = "id")
    private UserInfoEntity id_userinfo;
}
