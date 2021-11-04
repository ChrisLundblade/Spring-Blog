package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    Ad findByTitle(String title);

    @Query(nativeQuery = true, value="select * from ads where title like :t%")
    List<Ad> findByTitleLike(@Param("t") String term);

    Ad findByDescription(String description);
}
