package com.plit.real.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "portfolio_categories")
@Data
public class PortfolioCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String coverImage;
}
