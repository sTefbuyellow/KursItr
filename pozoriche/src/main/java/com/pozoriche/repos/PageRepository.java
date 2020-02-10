package com.pozoriche.repos;

import com.pozoriche.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository <Page, Long> {
}
