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
public class IntervalAndPrice implements Serializable {
    @NotNull(message = "{intervalAndPrice.startDate.notNull}")
    private Long startDate;
    @NotNull(message =  "{intervalAndPrice.endDate.notNull}")
    private Long endDate;
    @NotNull(message = "{intervalAndPrice.price.notNull}")
    private Double price;
}
