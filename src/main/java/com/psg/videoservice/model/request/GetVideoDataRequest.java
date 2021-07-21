package com.psg.videoservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetVideoDataRequest extends PaginatedRequest {

  private String title;
  private String description;

}
