package com.blog.back.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardListItem {
  private int boardNumber;
  private String title;
  private String content;
  private String boardTitleImage;
  private int favoriteCount;
  private int commentCount;
  private int viewCount;
  private String writeDateTime;
  private String writerNickName;
  private String writerProfileImage;
}
