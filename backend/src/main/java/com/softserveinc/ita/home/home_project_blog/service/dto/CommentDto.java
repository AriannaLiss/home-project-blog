package com.softserveinc.ita.home.home_project_blog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class CommentDto {
    private Long id;
    private String text;
    private PostDto post;
    private AuthorDto author;
    private Date createdOn;
    private Date updatedOn;
}
