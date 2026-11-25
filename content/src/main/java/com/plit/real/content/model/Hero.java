package com.plit.real.content.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hero_info")
@Data
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String subtitle;

    private String  buttonText;

    private String backgroundUrl;
}
