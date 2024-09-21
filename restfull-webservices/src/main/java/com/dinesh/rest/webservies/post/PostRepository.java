package com.dinesh.rest.webservies.post;

import com.dinesh.rest.webservies.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
