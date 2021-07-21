package com.psg.videoservice.outbound;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psg.videoservice.dao.ThumbnailDao;
import com.psg.videoservice.dao.VideoDataDao;
import com.psg.videoservice.entity.ThumbnailVideoEntity;
import com.psg.videoservice.entity.VideoDataEntity;
import com.psg.videoservice.model.response.YoutubeResponse;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/*
  author guneetginnigarg
 */
@Slf4j
@Service
public class YoutubeService {

  @Value("${google.youtube.api-key}")
  private String apiKey;

  @Value("${google.youtube.url}")
  private String youtubeUrl;

  private final RestTemplate restTemplate;

  private final VideoDataDao videoDataDao;

  private final ThumbnailDao thumbnailDao;

  private final ObjectMapper objectMapper;

  public YoutubeService(RestTemplate restTemplate, VideoDataDao videoDataDao,
      ThumbnailDao thumbnailDao, ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.videoDataDao = videoDataDao;
    this.thumbnailDao = thumbnailDao;
    this.objectMapper = objectMapper;
  }

  /**
   * Get Data for Youtube V3 API and save it to database after every 10000 ms
   */
  @Scheduled(fixedDelay = 10000)
  public void getDataFromGoogle() {
    Map<String, String> parameters = getRequestParameters();
    try {
      ResponseEntity<JSONObject> response = restTemplate.exchange(youtubeUrl, HttpMethod.GET, null,
                                                                  JSONObject.class, parameters);
      persistData(objectMapper.convertValue(response.getBody(), YoutubeResponse.class));
      log.info("Response: {}", response.getBody());
    } catch (RestClientException exception) {
      log.error("Exception while fetching data: {}", exception.getMessage());
    }
  }

  private Map<String, String> getRequestParameters() {
    Map<String, String> parameters = new HashMap<>();
    parameters.put("publishedAfter", getPublishedAfter());
    parameters.put("type", "video");
    parameters.put("key", apiKey);
    parameters.put("order", "date");
    parameters.put("q", "cricket");
    parameters.put("part", "snippet");
    return parameters;
  }

  private String getPublishedAfter() {
    return DateTimeFormatter.ISO_INSTANT.format(Instant.now().minusSeconds(1000000));
  }

  private void persistData(YoutubeResponse searchListResponse) {
    if (Objects.isNull(searchListResponse) || CollectionUtils.isEmpty(
        searchListResponse.getItems())) {
      return;
    }
    searchListResponse.getItems().forEach(this::persistData);
  }

  private void persistData(YoutubeResponse.Item item) {
    VideoDataEntity videoDataEntity = videoDataDao.saveVideoData(getVideoDataEntity(item));
    thumbnailDao.saveThumbnail(getThumbnails(videoDataEntity, item));
  }

  private VideoDataEntity getVideoDataEntity(YoutubeResponse.Item item) {
    YoutubeResponse.Snippet resultSnippet = item.getSnippet();
    return VideoDataEntity.builder().title(resultSnippet.getTitle()).description(
        resultSnippet.getDescription()).publishingTime(resultSnippet.getPublishedAt()).build();
  }

  private List<ThumbnailVideoEntity> getThumbnails(VideoDataEntity videoDataEntity,
      YoutubeResponse.Item item) {
    List<ThumbnailVideoEntity> thumbnails = new ArrayList<>();
    YoutubeResponse.Snippet resultSnippet = item.getSnippet();
    LinkedHashMap<String, YoutubeResponse.Thumbnail> thumbnailDetails = resultSnippet
        .getThumbnails();
    if (Objects.nonNull(thumbnailDetails)) {
      if (Objects.nonNull(thumbnailDetails.get("default"))) {
        thumbnails.add(getThumbnail(videoDataEntity, "default", thumbnailDetails.get("default")));
      }
      if (Objects.nonNull(thumbnailDetails.get("high"))) {
        thumbnails.add(getThumbnail(videoDataEntity, "high", thumbnailDetails.get("high")));
      }
      if (Objects.nonNull(thumbnailDetails.get("medium"))) {
        thumbnails.add(getThumbnail(videoDataEntity, "medium", thumbnailDetails.get("medium")));
      }
      if (Objects.nonNull(thumbnailDetails.get("standard"))) {
        thumbnails.add(getThumbnail(videoDataEntity, "standard", thumbnailDetails.get("standard")));
      }
    }
    return thumbnails;
  }

  private ThumbnailVideoEntity getThumbnail(VideoDataEntity videoDataEntity, String size,
      YoutubeResponse.Thumbnail thumbnail) {
    return ThumbnailVideoEntity.builder().size(size).url(thumbnail.getUrl()).video(videoDataEntity)
        .build();
  }

}
