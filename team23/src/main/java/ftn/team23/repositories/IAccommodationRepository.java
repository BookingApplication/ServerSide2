package ftn.team23.repositories;

import ftn.team23.entities.Accommodation;
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


    //  0  WAITING_CONFIRMATION,
    //  1  UPDATED,
    //  2  APPROVED,
    //  3  DENIED
    @Query(value = "SELECT a.id," +
            "              a.name," +
            "              a.description," +
            "              a.location," +
            "              a.min_guests as minNbOfGuests," +
            "              a.max_guests as maxNbOfGuests," +
            "              a.accommodation_type as accommodationType," +
            "              ARRAY(SELECT aa.amenities " +
            "                     FROM accommodation_amenities aa " +
            "                     WHERE aa.accommodation_id = a.id) AS amenities," +
            "             ARRAY(" +
//            "                  SELECT ROW(ai.start_date, ai.end_date, ai.price)\\:\\:interval_and_price " +
            "                  SELECT CONCAT(ai.start_date, ',', ai.end_date, ',', ai.price) AS interval_and_price " +
            "                  FROM available_intervals ai " +
            "                  WHERE ai.accommodation_id = a.id " +
            "                      AND (" +
            "                          (:startDate IS NOT NULL AND :endDate IS NOT NULL" +
            "                              AND ai.start_date >= :startDate " +
            "                              AND ai.end_date <= :endDate)" +
            "                          OR" +
            "                          (:startDate IS NOT NULL AND :endDate IS NULL " +
            "                              AND ai.start_date >= :startDate)" +
            "                          OR" +
            "                          (:startDate IS NULL AND :endDate IS NOT NULL" +
            "                              AND ai.end_date <= :endDate)" +
            "                          OR" +
            "                          (:startDate IS NULL AND :endDate IS NULL)" +
            "                          )" +
            "                          ) as intervalsAndPrices," +
            "              a.is_price_set_per_guest as isPriceSetPerGuest," +
            "              a.is_reservation_manual as isReservationManual," +
            "              ARRAY((SELECT concat(img.image_path ,',',img.name ,',', img.type,',', img.id)" +
            "                     FROM image img" +
            "                     WHERE img.accommodation_id = a.id)) as images"+
            " FROM accommodation AS a" +
            " JOIN available_intervals AS ai ON a.id = ai.accommodation_id" +
            " JOIN accommodation_amenities AS aa ON a.id = aa.accommodation_id " +
            " WHERE " +
            "  a.status = 2 " + //APPROVED
            "  AND (UPPER(a.location) LIKE UPPER('%' || :location ||'%') OR :location IS NULL)" +
            "  AND (:numberOfGuests IS NULL OR (a.min_guests <= :numberOfGuests AND a.max_guests >= :numberOfGuests))" +
            " GROUP BY a.id, a.name, a.location, a.min_guests, a.max_guests "+
            " HAVING  "+
            "           cardinality(ARRAY(" +
            "                  SELECT CONCAT(ai.start_date, ',', ai.end_date, ',', ai.price) as ddd" +
            "                  FROM available_intervals ai " +
            "                  WHERE ai.accommodation_id = a.id " +
            "                      AND (" +
            "                          (:startDate IS NOT NULL AND :endDate IS NOT NULL" +
            "                              AND ai.start_date >= :startDate " +
            "                              AND ai.end_date <= :endDate)" +
            "                          OR" +
            "                          (:startDate IS NOT NULL AND :endDate IS NULL " +
            "                              AND ai.start_date >= :startDate)" +
            "                          OR" +
            "                          (:startDate IS NULL AND :endDate IS NOT NULL" +
            "                              AND ai.end_date <= :endDate)" +
            "                          OR" +
            "                          (:startDate IS NULL AND :endDate IS NULL)" +
            "                          )" +
            "                          ) )  > 0",
            nativeQuery = true)
    List<Object> getAccommodationsBySearchCriteria(@Param("location") String location,
                                                   @Param("numberOfGuests") Integer numberOfGuests,
                                                   @Param("startDate") Long startDate,
                                                   @Param("endDate") Long endDate);
}
