package com.ouadie.kopi_luwak_backend.repositories;

import com.ouadie.kopi_luwak_backend.entities.Reservation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}