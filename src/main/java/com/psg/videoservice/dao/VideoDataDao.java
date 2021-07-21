package com.psg.videoservice.dao;

import com.psg.videoservice.entity.VideoDataEntity;
import com.psg.videoservice.model.request.GetVideoDataRequest;
import com.psg.videoservice.model.response.PaginatedResponse;
import com.psg.videoservice.repository.VideoRepository;
import com.psg.videoservice.util.PaginationUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * author guneetginnigarg
 */

@Component
public class VideoDataDao {

  private final VideoRepository videoDataRepository;

  public VideoDataDao(VideoRepository videoDataRepository) {
    this.videoDataRepository = videoDataRepository;
  }

  /**
   * Get Paginated Video Data from Database on the basis of the user request
   * @param request
   * @return
   */
  public PaginatedResponse<VideoDataEntity> getVideoData(GetVideoDataRequest request) {
    PageRequest pageRequest = PaginationUtils.getPageRequest(request.getPageNumber(),
                                                             request.getPageSize(),
                                                             "publishingTime", true);
    Page<VideoDataEntity> paginatedResponse = videoDataRepository.findAll(getSpecification(request),
                                                                          pageRequest);
    return new PaginatedResponse<>(paginatedResponse.getContent(),
                                   (int) paginatedResponse.getTotalElements());
  }

  /**
   * Save the video data into the database
   * @param videoDataEntity
   * @return
   */
  public VideoDataEntity saveVideoData(VideoDataEntity videoDataEntity) {
    return videoDataRepository.saveAndFlush(videoDataEntity);
  }

  /**
   * Get JPA Specification on the basis of the request
   * @param request
   * @return
   */
  private Specification<VideoDataEntity> getSpecification(GetVideoDataRequest request) {
    return (root, query, criteriaBuilder) -> {
      final List<Predicate> predicates = new ArrayList<>();
      Optional.ofNullable(request.getTitle()).ifPresent(
          value -> predicates.add(criteriaBuilder.equal(root.get("title"), value)));
      Optional.ofNullable(request.getDescription()).ifPresent(
          value -> predicates.add(criteriaBuilder.equal(root.get("description"), value)));
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
