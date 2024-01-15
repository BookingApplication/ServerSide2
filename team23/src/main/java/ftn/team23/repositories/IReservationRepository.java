package ftn.team23.repositories;

import ftn.team23.entities.Reservation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long>
{
    @Override
    List<Reservation> findAll(Sort sort);

    @Query("select r from Reservation r where r.accommodationId = ?1")
    List<Reservation> findAllByAccommodationId(Long accommodationId);
}
