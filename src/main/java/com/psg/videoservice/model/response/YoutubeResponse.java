package com.psg.videoservice.model.response;

import com.google.api.client.util.DateTime;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YoutubeResponse {

  private List<Item> items;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Item {

    private Snippet snippet;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Snippet {

    private DateTime publishedAt;
    private String title;
    private String description;
    private LinkedHashMap<String, Thumbnail> thumbnails;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Thumbnail {
    private String url;
  }

}
