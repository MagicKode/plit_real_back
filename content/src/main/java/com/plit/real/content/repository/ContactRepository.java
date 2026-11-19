package com.plit.real.content.repository;

import com.plit.real.content.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactInfo, Long> {
}
