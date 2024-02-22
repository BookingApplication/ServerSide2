package ftn.team23.repositories;

import ftn.team23.entities.Accommodation;
import ftn.team23.enums.AccommodationAmenity;
import ftn.team23.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IAccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("select a from Accommodation a where a.host.id = ?1")
    List<Accommodation> findByHost(long hostId);


    @Query("select a from Accommodation a join fetch a.images imgs where a.status = ?1")
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


    @Query("SELECT DISTINCT a " +
            "FROM Accommodation a " +
            "JOIN FETCH a.images " +
            "JOIN FETCH a.availableIntervalsAndPrices ai " +
            "JOIN FETCH a.amenities aa " +
            "WHERE a.status = 2 " +
            "AND (UPPER(a.location) LIKE UPPER(concat('%', :location, '%')) OR :location IS NULL) " +
            "AND (:numberOfGuests IS NULL OR (a.minGuests <= :numberOfGuests AND a.maxGuests >= :numberOfGuests)) " +
            "AND (SIZE(ai) > 0 " +
            "AND (:startDate IS NOT NULL AND :endDate IS NOT NULL " +
            "     AND ai.startDate >= :startDate AND ai.endDate <= :endDate) " +
            "OR (:startDate IS NOT NULL AND :endDate IS NULL " +
            "     AND ai.startDate >= :startDate) " +
            "OR (:startDate IS NULL AND :endDate IS NOT NULL " +
            "     AND ai.endDate <= :endDate) " +
            "OR (:startDate IS NULL AND :endDate IS NULL)) " +
            "AND (:amenities IS NULL OR EXISTS " +
            "       (SELECT 1 FROM a.amenities am " +
            "        WHERE am IN :amenities " +
            "        GROUP BY a HAVING COUNT(DISTINCT am) = :amenitiesCount)) " +
            "AND (:accommodationType = '' OR a.accommodationType = :accommodationType) " +
            "AND (:minPrice IS NULL OR ai.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR ai.price <= :maxPrice) ")
    List<Accommodation> getAccommodationsBySearchCriteria(
                                            @Param("location") String location,
                                            @Param("numberOfGuests") Integer numberOfGuests,
                                            @Param("startDate") Long startDate,
                                            @Param("endDate") Long endDate,
                                            @Param("amenities") Collection<AccommodationAmenity> amenities,
                                            @Param("accommodationType") String accommodationType,
                                            @Param("minPrice") Double minPrice,
                                            @Param("maxPrice") Double maxPrice,
                                            @Param("amenitiesCount") Long amenitiesCount);



    @Query("SELECT DISTINCT a FROM Accommodation a " +
            "JOIN FETCH a.images " +
            "JOIN FETCH a.availableIntervalsAndPrices ai " +
            "WHERE (:amenities IS NULL OR EXISTS " +
            "       (SELECT 1 FROM a.amenities am " +
            "        WHERE am IN :amenities " +
            "        GROUP BY a HAVING COUNT(DISTINCT am) = :amenitiesCount)) " +
            "AND (:accommodationType = '' OR a.accommodationType = :accommodationType) " +
            "AND (:minPrice IS NULL OR ai.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR ai.price <= :maxPrice) " +
            "AND (a.status = 2)")
    List<Accommodation> findFilteredAccommodations(
            @Param("amenities") Collection<AccommodationAmenity> amenities,
            @Param("accommodationType") String accommodationType,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("amenitiesCount") Long amenitiesCount
    );

}
