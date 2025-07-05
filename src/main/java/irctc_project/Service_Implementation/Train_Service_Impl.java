package irctc_project.Service_Implementation;

import irctc_project.Paiging.PagedResponce;
import irctc_project.Repository.StationRepo;
import irctc_project.Repository.TrainRepo;
import irctc_project.Service.TrainService;
import irctc_project.dto.TrainDto;
import irctc_project.entity.Station;
import irctc_project.entity.Train;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class Train_Service_Impl implements TrainService
{

    StationRepo stationRepo;
    ModelMapper modelMapper;
    TrainRepo trainRepo;

    //👉 This will do Automatic Constructor Injection


    public Train_Service_Impl(StationRepo stationRepo, ModelMapper modelMapper, TrainRepo trainRepo) {
        this.stationRepo = stationRepo;
        this.modelMapper = modelMapper;
        this.trainRepo = trainRepo;
    }

    @Override
    public TrainDto createTrain(TrainDto trainDto)
    {
        Station sourceStation = stationRepo.findById(trainDto.getSourceStation().getId()).orElseThrow(() -> new NoSuchElementException("Source Station not found"));
        Station destinationStation = stationRepo.findById(trainDto.getDestinationStation().getId()).orElseThrow(() -> new NoSuchElementException("Destination Station not found"));

        Train train = modelMapper.map(trainDto, Train.class);

        //👉 Set the source and destination stations
        train.setSourceStation(sourceStation);
        train.setDestinationStation(destinationStation);

        //👉 Save Train to DB
        Train savedtrain = trainRepo.save(train);

        //👉 Convert Train to TrainDto
        return modelMapper.map(savedtrain,TrainDto.class);
    }



    @Override
    public PagedResponce<TrainDto> getAllTrain(int page, int size, String sortBy, String sortDir)
    {

        //✅ 1. Sort: we use Conditional operator here
        Sort sort = sortDir.trim().toLowerCase().equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //✅ 2. Pageable : it holds info. related to Paiging
        Pageable pageable = PageRequest.of(page,size,sort);

        //✅ 3. Page : It have info of Paiging + Content from DB
        Page<Train> trains = trainRepo.findAll(pageable);


        //👉 Convert Train to TrainDto
        Page<TrainDto> trainDtos = trains.map(train -> modelMapper.map(train, TrainDto.class));

        return PagedResponce.fromPage(trainDtos);

    }




    @Override
    public TrainDto getTrainById(Long trainId)
    {
        Train train = trainRepo.findById(trainId).orElseThrow(()-> new NoSuchElementException("Train not found with ID: " + trainId));

        //👉 Convert Train to TrainDto
        TrainDto trainDto = modelMapper.map(train, TrainDto.class);

        return trainDto;
    }





    @Override
    public void deleteTrainById(Long trainId)
    {
        trainRepo.deleteById(trainId);
    }





    @Override
    public TrainDto updateTrainById(Long trainId, TrainDto trainDto)
    {

        Train train = trainRepo.findById(trainId).orElseThrow(() -> new NoSuchElementException("Train not found with ID: " + trainId));

        //👉 Update Train properties

        train.setName(trainDto.getName());

        train.setNumber(trainDto.getNumber());

        train.setSourceStation(stationRepo.findById(trainDto.getSourceStation().getId())
                .orElseThrow(() -> new NoSuchElementException("Source Station not found")));

        train.setDestinationStation(stationRepo.findById(trainDto.getDestinationStation().getId())
                .orElseThrow(() -> new NoSuchElementException("Destination Station not found")));

        train.setTotalDistance(trainDto.getTotalDistance());

        Train savedTrain = trainRepo.save(train);

        //👉 Convert Train to TrainDto

        TrainDto dto = modelMapper.map(savedTrain, TrainDto.class);

        return dto;
    }


}
