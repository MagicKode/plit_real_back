package com.plit.real.content.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "contacts")
@Data
@AllArgsConstructor
public class ContactInfo {

    @Id
    private Long id = 1L;

    @Column(nullable = false)
    private String phone;

    private String telegram;

    private String viber;

    private String address;

    public ContactInfo() {
    }
}
