package com.plit.real.content.repository;

import com.plit.real.content.model.AboutInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutInfoRepository extends JpaRepository<AboutInfo, Long> {
}
