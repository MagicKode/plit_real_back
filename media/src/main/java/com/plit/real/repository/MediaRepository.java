package com.plit.real.repository;

import com.plit.real.model.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<MediaFile, Long> {
    List<MediaFile> findAllByCategoryOrderByIdDesc(String category);
}
