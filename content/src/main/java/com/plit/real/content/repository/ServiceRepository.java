package com.plit.real.content.repository;

import com.plit.real.content.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity,Long> {
    List<ServiceEntity> findAllByOrderByIdAsc();
}
