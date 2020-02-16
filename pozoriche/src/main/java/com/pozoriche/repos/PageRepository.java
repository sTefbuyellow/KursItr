package com.pozoriche.repos;

import com.pozoriche.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageRepository extends JpaRepository <Page, Long> {

    List<Page> findByTagOrName(String tag, String name);
}
