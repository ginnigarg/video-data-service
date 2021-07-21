package com.psg.videoservice.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * author guneetginnigarg
 */

public class PaginationUtils {

  /**
   *
   * @param pageNumber
   * @param offset
   * @param sortBy
   * @param isAsc
   * @return
   */
  public static PageRequest getPageRequest(
      int pageNumber, int offset, String sortBy, boolean isAsc) {
    Sort sort = Sort.by(sortBy);
    if (isAsc) {
      sort.ascending();
    } else {
      sort.descending();
    }
    return PageRequest.of(pageNumber - 1, offset, sort);
  }

  /**
   *
   * @param pageNumber
   * @param offset
   * @param sort
   * @return
   */
  public static PageRequest getPageRequest(int pageNumber, int offset, Sort sort) {
    return PageRequest.of(pageNumber - 1, offset, sort);
  }

}
