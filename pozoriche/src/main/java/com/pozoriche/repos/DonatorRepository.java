package com.pozoriche.repos;

import com.pozoriche.model.Donator;
import com.pozoriche.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonatorRepository extends JpaRepository<Donator, Long> {

    List<Donator> findByDonator(User user);
}
