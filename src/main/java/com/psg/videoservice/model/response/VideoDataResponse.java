package com.psg.videoservice.model.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.api.client.util.DateTime;
import com.psg.videoservice.entity.ThumbnailVideoEntity;
import com.psg.videoservice.entity.VideoDataEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VideoDataResponse {

  private String title;
  private String description;
  private DateTime publishedAt;
  private List<Thumbnail> thumbnails;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Thumbnail {

    private String url;
    private String size;
  }

  public VideoDataResponse(VideoDataEntity videoDataEntity) {
    this.title = videoDataEntity.getTitle();
    this.description = videoDataEntity.getDescription();
    this.publishedAt = videoDataEntity.getPublishingTime();
    List<ThumbnailVideoEntity> thumbnailVideoEntities = videoDataEntity.getThumbnails();
    if (CollectionUtils.isEmpty(thumbnailVideoEntities)) {
      this.thumbnails = new ArrayList<>();
    } else {
      this.thumbnails = thumbnailVideoEntities.stream().map(this::getThumbnail).collect(
          Collectors.toList());
    }
  }

  private Thumbnail getThumbnail(ThumbnailVideoEntity thumbnailVideoEntity) {
    return Thumbnail.builder().url(thumbnailVideoEntity.getUrl()).size(
        thumbnailVideoEntity.getSize()).build();
  }

}
