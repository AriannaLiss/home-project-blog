package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.PostRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Post;
import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.PostMapperService;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@Service
public class PostService implements IPostService {

    private final PostRepository repository;
    private final PostMapperService mapper;
    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;

    @Override
    public Page<PostDto> findAll(Long id, String title, Integer pageNum, Integer pageSize, String sort) {
        Pageable paging = GeneralService.pagination(pageNum, pageSize, sort);
        Page<Post> postsPage;
//        if ((title != null) && (id != null)) {
//            postsPage = repository.findByTitleAndId(title, id, paging);
//        } else if (title != null) {
//            postsPage = repository.findByTitle(title, paging);
//        } else
        if (id != null) {
            postsPage = repository.findById(id, paging);
        } else {
            postsPage = repository.findAll(paging);
        }
        return mapper.toPagePostDto(postsPage);
    }

    @Override
    public PostDto getById(Long id) {
        return mapper.toPostDto(repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Const.POST_DOESNT_EXIST)));
    }


//    private void throwIfEmailIsNotUnique(String email) {
//        if (repository.existsByEmail(email)) {
//            throw new NotUniqueException(Const.EMAIL_IS_NOT_UNIQUE);
//        }
//    }
//
//    private void throwIfNameIsNotUnique(String name) {
//        if (repository.existsByName(name)) {
//            throw new NotUniqueException(Const.NAME_IS_NOT_UNIQUE);
//        }
//    }

    @Override
    public PostDto save(@Valid PostDto post) {
//        throwIfEmailIsNotUnique(post.getEmail());
//        throwIfNameIsNotUnique(post.getName());
//        post.setRole(Role.BLOGGER);
//        post.setPassword(passwordEncoder.encode(post.getPassword()));
        post.setUser(userService.getCurrentUser());
        Post post1 = mapper.toPost(post);
        Post post2 = repository.save(post1);
        PostDto postDto = mapper.toPostDto(post2);
        return postDto;
//        return mapper.toPostDto(repository.save(mapper.toPost(post)));
    }

//    private PostDto updateDto(PostDto oldPost, PostDto newPost) {
//        if (!oldPost.getEmail().equalsIgnoreCase(newPost.getEmail())) {
//            throwIfEmailIsNotUnique(newPost.getEmail());
//        }
//        if (!oldPost.getName().equalsIgnoreCase(newPost.getName())) {
//            throwIfNameIsNotUnique(newPost.getName());
//        }
//        newPost.setId(oldPost.getId());
//        newPost.setPassword(oldPost.getPassword());
//        newPost.setRole(oldPost.getRole());
//        return mapper.toPostDto(repository.save(mapper.toPost(newPost)));
//    }

//    @Override
//    public PostDto update(Long id, @Valid PostDto post) {
//        return updateDto(getById(id), post);
//    }
//
//    @Override
//    public PostDto updateCurrentPost(@Valid PostDto post) {
//        return updateDto(getCurrentPost(), post);
//    }
//
//    @Override
//    public void delete(Long id) {
//        if (!repository.existsById(id)) {
//            throw new EntityNotFoundException(Const.USER_DOESNT_EXIST);
//        }
//        repository.deleteById(id);
//    }
}
