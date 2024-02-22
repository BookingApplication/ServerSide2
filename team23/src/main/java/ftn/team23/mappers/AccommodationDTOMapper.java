package ftn.team23.mappers;
import ftn.team23.dto.SearchedAccommodationDTO;
import ftn.team23.entities.Accommodation;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class AccommodationDTOMapper {

    public static ModelMapper modelMapper = new ModelMapper();

    public AccommodationDTOMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public static List<SearchedAccommodationDTO> toSearchedAccommodationDTOs(List<Object> objects)
    {
        List<SearchedAccommodationDTO> dtos = new ArrayList<>();
        for(Object o : objects)
        {
            dtos.add(toSearchedAccommodationDTO(o));
        }
        return dtos;
    }

    public static SearchedAccommodationDTO toSearchedAccommodationDTO(Object o)
    {
        SearchedAccommodationDTO dto = new SearchedAccommodationDTO((Object[]) o);
        return dto;
    }

    public static SearchedAccommodationDTO accommodationToSearchedAccommodationDTO(Accommodation a){
        return new SearchedAccommodationDTO(a);
    }

    public static List<SearchedAccommodationDTO> accommodationsToSearchedAccommodationDTOs(List<Accommodation> accommodations){
        List<SearchedAccommodationDTO> dtos = new ArrayList<>();
        for(Accommodation a : accommodations){
            SearchedAccommodationDTO dto = accommodationToSearchedAccommodationDTO(a);
            dtos.add(dto);
        }
        return dtos;
    }

}
