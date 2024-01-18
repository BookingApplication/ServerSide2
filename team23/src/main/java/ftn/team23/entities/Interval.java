package ftn.team23.entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Interval implements Serializable {
    @NotNull(message = "{interval.startDate.notNull}")
    private Long startDate;
    @NotNull(message =  "{interval.endDate.notNull}")
    private Long endDate;
}
