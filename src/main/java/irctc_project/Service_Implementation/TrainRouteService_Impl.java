package irctc_project.Service_Implementation;

import irctc_project.Repository.StationRepo;
import irctc_project.Repository.TrainRepo;
import irctc_project.Repository.TrainRouteRepo;
import irctc_project.Service.TrainRouteService;

import irctc_project.dto.TrainRouteDto;
import irctc_project.entity.Station;
import irctc_project.entity.Train;
import irctc_project.entity.TrainRoute;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;



@Service
public class TrainRouteService_Impl implements TrainRouteService
{
    TrainRepo trainRepo;
    StationRepo stationRepo;
    TrainRouteRepo trainRouteRepo;
    ModelMapper modelMapper;

    //// 👉 Constructor-based dependency injection
    public TrainRouteService_Impl(TrainRepo trainRepo, StationRepo stationRepo, TrainRouteRepo trainRouteRepo, ModelMapper modelMapper) {
        this.trainRepo = trainRepo;
        this.stationRepo = stationRepo;
        this.trainRouteRepo = trainRouteRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrainRouteDto addTrainRoute(TrainRouteDto trainRouteDto)
    {
        //👉 Fetching tRAIN FROM DB
        Train train = trainRepo.findById(trainRouteDto.getTrain_id()).orElseThrow(()->new NoSuchElementException("Train not found with ID: " + trainRouteDto.getTrain_id()));

        //👉 Fetching station from DB
        Station station = stationRepo.findById(trainRouteDto.getStation_id()).orElseThrow(()->new NoSuchElementException("Station not found with ID: " + trainRouteDto.getStation_id()));

        TrainRoute trainRoute = modelMapper.map(trainRouteDto, TrainRoute.class);

        //// 👉 Save the train and station to the Db
        trainRoute.setTrain(train);
        trainRoute.setStation(station);

        TrainRoute savedRoute = trainRouteRepo.save(trainRoute);

        // Manually build the response DTO
        TrainRouteDto responseDto = modelMapper.map(savedRoute, TrainRouteDto.class);
        responseDto.setTrain_id(train.getId());
        responseDto.setStation_id(station.getId());

        return responseDto;

    }

    @Override
    public List<TrainRouteDto> getTrainRoutesByTrainId(Long trainId) {
        List<TrainRoute> trainRoutes = trainRouteRepo.findByTrainId(trainId);

        return trainRoutes.stream().map(trainRoute -> {
            TrainRouteDto dto = modelMapper.map(trainRoute, TrainRouteDto.class);
            dto.setTrain_id(trainRoute.getTrain().getId());
            dto.setStation_id(trainRoute.getStation().getId());
            return dto;
        }).toList();
    }

    @Override
    public TrainRouteDto updateTrainRoute(Long id, TrainRouteDto trainRouteDto) {

        TrainRoute existingRoute = trainRouteRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Train Route not found with ID: " + id));

        // Update the fields
        existingRoute.setStationorder(trainRouteDto.getStationorder());
        existingRoute.setArrivalTime(trainRouteDto.getArrivalTime());
        existingRoute.setDepartureTime(trainRouteDto.getDepartureTime());
        existingRoute.setHaltMinutes(trainRouteDto.getHaltMinutes());
        existingRoute.setDistanceFromSource(trainRouteDto.getDistanceFromSource());

        // Save the updated route
        TrainRoute updatedRoute = trainRouteRepo.save(existingRoute);

        // Map to DTO
        TrainRouteDto responseDto = modelMapper.map(updatedRoute, TrainRouteDto.class);
        responseDto.setTrain_id(updatedRoute.getTrain().getId());
        responseDto.setStation_id(updatedRoute.getStation().getId());

        return responseDto;
    }

    @Override
    public void deleteTrainRoute(Long id) {

        TrainRoute trainRoute = trainRouteRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Train Route not found with ID: " + id));

        trainRouteRepo.delete(trainRoute);

    }
}
