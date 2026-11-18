package com.plit.real.content.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "about_info")
@Data
public class AboutInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String masterName;

    private String experience;

    @Column(length = 1000)
    private String description;

    private String photoUrl;
}
