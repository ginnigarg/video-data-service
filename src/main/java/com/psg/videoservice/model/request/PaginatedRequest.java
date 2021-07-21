package com.psg.videoservice.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedRequest {

  @Min(value = 1, message = "The page number can not be less than 1")
  private Integer pageNumber = 1;

  @Max(value = 1000, message = "The page size can not be greater than 1000")
  private Integer pageSize = 10;

}
