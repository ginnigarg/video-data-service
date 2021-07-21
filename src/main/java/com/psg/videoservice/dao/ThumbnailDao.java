package com.psg.videoservice.dao;

import com.psg.videoservice.entity.ThumbnailVideoEntity;
import com.psg.videoservice.repository.ThumbnailRepository;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * author guneetginnigarg
 */

@Component
public class ThumbnailDao {

  private final ThumbnailRepository thumbnailRepository;

  public ThumbnailDao(ThumbnailRepository thumbnailRepository) {
    this.thumbnailRepository = thumbnailRepository;
  }

  /**
   *
   * @param thumbnailVideoEntity
   * @return
   */
  public List<ThumbnailVideoEntity> saveThumbnail(List<ThumbnailVideoEntity> thumbnailVideoEntity) {
    return thumbnailRepository.saveAllAndFlush(thumbnailVideoEntity);
  }
}
