package ftn.team23.repositories;

import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IHostRepository extends JpaRepository<Host, Long>{

    @Override
    void deleteById(Long aLong);

    Optional<Host> findByEmail(String email);
    Optional<Host> findByEmailAndPassword(String email, String password);
    Optional<Host> findByCodeActivation(String codeActivation);

    @Transactional
    @Modifying
    @Query("UPDATE Host h SET h.activated = :activated WHERE h.codeActivation = :codeActivation")
    void updateActivationStatusByCodeActivation(@Param("codeActivation") String codeActivation, @Param("activated") Boolean activated);

    @Transactional
    @Modifying
    @Query("update Host h SET h.profilePicture = ?2 where h.id = ?1")
    void updateProfilePicture(Long id, String fileName);

    @Query("select h from Host h join fetch h.accommodations a where h.id = ?1")
    Optional<Host> getHostWithAccommodations(Long id);

    @Query(value = "SELECT h.id, a.id, a.host_id, r.* FROM host h " +
            "JOIN accommodation a ON h.id = a.host_id " +
            "LEFT JOIN reservation r ON a.id = r.accommodation_id AND r.status = 2 " +
            "WHERE h.id = ?1", nativeQuery = true)
    List<Object[]> getHostWithAccommodationsAndReservations(Long hostId);

}
