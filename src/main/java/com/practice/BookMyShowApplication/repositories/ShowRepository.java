package com.practice.BookMyShowApplication.repositories;

import com.practice.BookMyShowApplication.models.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShowRepository extends JpaRepository<Shows, Long> {

    @Override
    Optional<Shows> findById(Long showId);
}
