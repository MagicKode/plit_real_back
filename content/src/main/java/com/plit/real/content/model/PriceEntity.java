package com.plit.real.content.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "prices")
@Data
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false, length = 100)
    private String price;

    @Column(length = 50)
    private String currency;
}
