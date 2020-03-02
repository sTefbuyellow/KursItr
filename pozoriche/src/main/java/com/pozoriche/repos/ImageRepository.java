package com.pozoriche.repos;

import com.pozoriche.model.Donator;
import com.pozoriche.model.Image;
import com.pozoriche.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByImageURL(String imageURL);
}
