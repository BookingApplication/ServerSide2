package ftn.team23.entities;

import ftn.team23.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Reservation {

    @Id
    private Long id;
    private Long accommodationId;
    private Long startDate;
    private Long endDate;
    private Status status;
    @Column(name = "deleted")
    private boolean deleted;
}
