package com.example.legendcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private ItemType itemType;

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private Category category;

    private long quantity;
    private LocalDateTime actionDate;
    private String actionType;


}
