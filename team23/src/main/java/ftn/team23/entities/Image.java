package ftn.team23.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    private Long id;

    private String imagePath;
    private String name;
    private String type;
    @Transient
    private byte[] picByte;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    public Image(String imagePath, String name, String type) {
        this.imagePath = imagePath;
        this.name = name;
        this.type = type;
    }
    public Image(String imagePath, String name, String type, byte[] bytes) {
        this.imagePath = imagePath;
        this.name = name;
        this.type = type;
        this.picByte = bytes;
    }
    /*
     * Hibernate zahteva da entitet mora biti jednak samom sebi kroz sva
     * stanja tog objekta (tranzijentni (novi objekat), perzistentan (persistent), otkacen (detached) i obrisan (removed)).
     * To znaci da bi dobar pristup bio da se za generisanje equals i hashCode metoda koristi podatak
     * koji je jedinstven a poznat unapred (tzv. business key) npr. index studenta, isbn knjige, itd.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(imagePath, image.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imagePath);
    }

}
