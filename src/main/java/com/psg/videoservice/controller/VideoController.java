package com.psg.videoservice.controller;

import com.psg.videoservice.model.request.GetVideoDataRequest;
import com.psg.videoservice.model.request.PaginatedRequest;
import com.psg.videoservice.model.response.PaginatedResponse;
import com.psg.videoservice.model.response.VideoDataResponse;
import com.psg.videoservice.service.VideoService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author guneetginnigarg
 */

@RestController
@RequestMapping("/v1/videos")
public class VideoController {

  private final VideoService videoservice;

  public VideoController(VideoService videoservice) {
    this.videoservice = videoservice;
  }

  /**
   *
   * @param request
   * @return
   */
  @GetMapping
  public PaginatedResponse<VideoDataResponse> getVideoData(@Valid GetVideoDataRequest request) {
    return videoservice.getVideoData(request);
  }

}
