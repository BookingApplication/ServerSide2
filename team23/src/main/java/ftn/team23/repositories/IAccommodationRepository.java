package ftn.team23.repositories;

import ftn.team23.controller.AccommodationController;
import ftn.team23.entities.Accommodation;
import ftn.team23.entities.Host;
import ftn.team23.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IAccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("select a from Accommodation a where a.host.id = ?1")
    List<Accommodation> findByHost(long hostId);


    @Query("select a from Accommodation a where a.status = ?1")
    List<Accommodation> findAccommodationByStatus(Status status);

    @Query("select a from Accommodation a join fetch a.images imgs where a.status = ?1")
    List<Accommodation> findAccommodationByStatusWithImages(Status status);


    @Transactional
    @Modifying
    @Query("update Accommodation a set a.status = ?2 where a.id = ?1")
    void changeStatus(Long id, Status status);

    @Query("select a from Accommodation a join fetch a.images imgs where a.id = ?1")
    Optional<Accommodation> findByIdWithImages(Long id);

    @Transactional
    @Modifying
    @Query("update Accommodation a set a.host = null where a.id = ?1")
    void setDeleted(long id);
}
