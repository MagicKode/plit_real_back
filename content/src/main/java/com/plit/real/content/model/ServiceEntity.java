package com.plit.real.content.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "services")
@Data
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(length = 50)
    private String icon;



}
