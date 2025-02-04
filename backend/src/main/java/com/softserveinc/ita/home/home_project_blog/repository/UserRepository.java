package com.softserveinc.ita.home.home_project_blog.repository;

import com.softserveinc.ita.home.home_project_blog.repository.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    Page<User> findByNameAndId(String name, Long id, Pageable paging);

    Page<User> findByName(String name, Pageable paging);

    Page<User> findById(Long Id, Pageable paging);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByName(String name);
}
