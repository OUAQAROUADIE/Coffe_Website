package com.ouadie.kopi_luwak_backend.controllers;

import com.ouadie.kopi_luwak_backend.entities.Reservation;
import com.ouadie.kopi_luwak_backend.repositories.ReservationRepository;
import com.ouadie.kopi_luwak_backend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:63342", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final EmailService emailService;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, EmailService emailService) {
        this.reservationRepository = reservationRepository;
        this.emailService = emailService;
    }

    @GetMapping
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        try {
            Reservation savedReservation = reservationRepository.save(reservation);

            // Envoyer la réservation par e-mail
            String to = "ouaquarouadie@gmail.com";
            String subject = "Nouvelle Réservation";
            String body = "Une nouvelle réservation a été effectuée. \n\n" +
                    "Détails de la réservation:\n" +
                    "Nom: " + reservation.getNom() + "\n" +
                    "E-mail: " + reservation.getEmail() + "\n" +
                    "Date: " + reservation.getDate() + "\n" +
                    "Heure: " + reservation.getTime() + "\n" +
                    "Nombre de personnes: " + reservation.getNombre_person();

            emailService.sendReservationConfirmation(to, subject, body);

            return new ResponseEntity<>("Réservation réussie avec l'ID : " + savedReservation.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la réservation : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
