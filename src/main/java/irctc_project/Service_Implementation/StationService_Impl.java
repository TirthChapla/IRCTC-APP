package irctc_project.Service_Implementation;

import irctc_project.Paiging.PagedResponce;
import irctc_project.Repository.StationRepo;
import irctc_project.Service.StationService;
import irctc_project.dto.StationDto;
import irctc_project.entity.Station;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StationService_Impl implements StationService {

    private StationRepo stationRepo;

    private ModelMapper modelMapper;


    //👉This will do Construction Injection
    public StationService_Impl(ModelMapper modelMapper, StationRepo stationRepo)
    {
        this.modelMapper = modelMapper;
        this.stationRepo = stationRepo;
    }


    @Override
    public StationDto createStation(StationDto stationDto)
    {

        //👉 StationDto to Station
        Station station =  modelMapper.map(stationDto ,Station.class);

        //👉 Save Station to DB
        stationRepo.save(station);

        return stationDto;
    }


    @Override
    public PagedResponce<StationDto> getAllStations(int page, int size, String sortBy, String sortDir)
    {

        //✅ 1. Sort: we use Conditional operator here
        Sort sort = sortDir.trim().toLowerCase().equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //✅ 2. Pageable : it holds info. related to Paiging
        Pageable pageable = PageRequest.of(page,size,sort);

        //✅ 3. Page : It have info of Paiging + Content from DB
        Page<Station> stations = stationRepo.findAll(pageable);


        //👉 Convertng Station to StationDto

        Page<StationDto> stationDtos = stations.map(station -> modelMapper.map(station,StationDto.class) );


        return PagedResponce.fromPage(stationDtos);
    }


    @Override
    public StationDto getStationById(Long id)
    {

        //👉 it will find the station By id
        Station station = stationRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Station is Not Found In Database"));

        StationDto stationDto = modelMapper.map(station,StationDto.class);

        return stationDto;
    }


    @Override
    public StationDto updateStations(Long id, StationDto stationDto)
    {
        //👉 it will find the station By id
        Station station = stationRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Station is Not Found In Database"));

        //👉 it will Update the fields
        station.setId(id);
        station.setCode(stationDto.getCode());
        station.setName(stationDto.getName());
        station.setCity(stationDto.getCity());
        station.setState(stationDto.getState());

        //👉 save to DB
        Station updatedStation = stationRepo.save(station);

        //// 👉 converting station to StationDto
        return modelMapper.map(station,StationDto.class);

    }



    @Override
    public void deleteStations(Long id)
    {
        stationRepo.deleteById(id);

    }
}
