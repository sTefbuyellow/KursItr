package com.pozoriche.repos;

import com.pozoriche.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    List<Page> findByTagOrName(String tag, String name);
    Page findOneByName(String name);
}
