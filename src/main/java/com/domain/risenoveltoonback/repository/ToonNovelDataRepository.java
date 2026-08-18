package com.domain.risenoveltoonback.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.risenoveltoonback.entity.ContentsEntity;

public interface ToonNovelDataRepository extends JpaRepository<ContentsEntity, Long> {
}
