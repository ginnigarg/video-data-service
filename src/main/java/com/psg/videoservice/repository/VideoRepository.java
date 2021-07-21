package com.psg.videoservice.repository;

import com.psg.videoservice.entity.VideoDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoDataEntity, Long>,
    JpaSpecificationExecutor<VideoDataEntity> {

}
