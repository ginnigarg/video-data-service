package com.psg.videoservice.repository;

import com.psg.videoservice.entity.ThumbnailVideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThumbnailRepository extends JpaRepository<ThumbnailVideoEntity, Long> {

}
