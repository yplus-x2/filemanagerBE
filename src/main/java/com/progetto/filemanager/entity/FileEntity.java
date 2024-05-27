package com.progetto.filemanager.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table (name = "file")
public class FileEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private String date;


    //----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN----JOIN

    @ManyToOne
    @JoinColumn(name = "id_category")
    @JsonManagedReference
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonManagedReference
    private UserEntity userEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_blob", referencedColumnName = "id")
    private BlobEntity blob;
}
