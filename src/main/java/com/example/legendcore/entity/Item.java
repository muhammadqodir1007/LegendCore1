package com.example.legendcore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor

public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private long quantity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Admin admin;


    @ManyToOne
    private ItemType itemType;

    @ManyToOne
    private Category category;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
