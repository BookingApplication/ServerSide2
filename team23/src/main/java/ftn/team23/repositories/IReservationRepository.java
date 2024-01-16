package ftn.team23.repositories;

import ftn.team23.entities.Reservation;
import ftn.team23.enums.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long>
{
    @Query("select r from Reservation r where r.accommodationId = ?1")
    List<Reservation> findAllByAccommodationId(Long accommodationId);

    @Query("select r from Reservation r where r.status = ?1")
    List<Reservation> findByStatus(Status status);

    @Query("select r from Reservation r where r.guest.id = ?1")
    List<Reservation> findAllByGuestId(Long guestId);

}
