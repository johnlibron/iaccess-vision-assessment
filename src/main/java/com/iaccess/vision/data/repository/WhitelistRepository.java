package com.iaccess.vision.data.repository;

import com.iaccess.vision.data.entity.WhitelistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WhitelistRepository extends JpaRepository<WhitelistEntity, Long> {

}
