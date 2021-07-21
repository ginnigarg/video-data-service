package com.psg.videoservice.service;

import com.psg.videoservice.dao.VideoDataDao;
import com.psg.videoservice.entity.VideoDataEntity;
import com.psg.videoservice.model.request.GetVideoDataRequest;
import com.psg.videoservice.model.response.PaginatedResponse;
import com.psg.videoservice.model.response.VideoDataResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * author guneetginnigarg
 */

@Service
public class VideoService {

  private final VideoDataDao videoDataDao;

  public VideoService(VideoDataDao videoDataDao) {
    this.videoDataDao = videoDataDao;
  }

  /**
   * Get Video Data from data on the basis of the request from the client
   * @param request
   * @return
   */
  public PaginatedResponse<VideoDataResponse> getVideoData(GetVideoDataRequest request) {
    PaginatedResponse<VideoDataEntity> entities = videoDataDao.getVideoData(request);
    List<VideoDataResponse> responseList = entities.getData().stream().map(VideoDataResponse::new)
        .collect(Collectors.toList());
    return new PaginatedResponse<>(responseList, entities.getTotal());
  }

}
