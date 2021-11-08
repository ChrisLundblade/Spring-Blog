package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String title);

    @Query(nativeQuery = true, value="select * from posts where title like :t%")
    List<Post> findByTitleLike(@Param("t") String term);

    @Query(nativeQuery = true, value="UPDATE posts SET title =t, body=b   where id=ident")
    List<Post> editByID(@Param("ident") long ident, @Param("title") String t, @Param("body") String b);

//    Post findByDescription(String description);
}
