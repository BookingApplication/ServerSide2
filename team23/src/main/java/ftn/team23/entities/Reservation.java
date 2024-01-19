package ftn.team23.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ftn.team23.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Reservation {

    @Id
    private Long id;
    @NotNull(message = "{reservation.accommodationId.notNull}")
    private Long accommodationId;
    @NotNull(message = "{reservation.startDate.notNull}")
    private Long startDate;
    @NotNull(message = "{reservation.endDate.notNull}")
    private Long endDate;
    @NotNull(message = "{reservation.status.notNull}")
    private Status status;
    @Column(name = "deleted")
    @NotNull(message = "{reservation.deleted.notNull}")
    private boolean deleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Guest guest;
}
