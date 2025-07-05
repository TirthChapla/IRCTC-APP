package irctc_project.Service;

import irctc_project.Paiging.PagedResponce;
import irctc_project.dto.StationDto;
import jakarta.validation.Valid;
import lombok.Lombok;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//❤️ We create Service as Interface because We want Loose Coupling
//      - whenever we want to change then only change the method of this

@Service
public interface StationService
{

    StationDto createStation(@Valid StationDto stationDto);

    PagedResponce<StationDto> getAllStations(int page, int size, String sortBy, String sortDir);

    StationDto getStationById(Long id);

    StationDto updateStations(Long id, @Valid StationDto stationDto);

    void deleteStations(Long id);
}
