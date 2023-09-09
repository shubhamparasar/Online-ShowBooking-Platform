package com.practice.BookMyShowApplication.repositories;

import com.practice.BookMyShowApplication.models.ShowSeatType;
import com.practice.BookMyShowApplication.models.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

    List<ShowSeatType> findAllByShows(Shows show);
}
