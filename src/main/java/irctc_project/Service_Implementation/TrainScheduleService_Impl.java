package irctc_project.Service_Implementation;

import irctc_project.Repository.TrainRepo;
import irctc_project.Repository.TrainScheduleRepo;
import irctc_project.Service.TrainScheduleService;
import irctc_project.dto.TrainScheduleDto;
import irctc_project.entity.Train;
import irctc_project.entity.TrainSchedule;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Service

public class TrainScheduleService_Impl implements TrainScheduleService
{
    /// 👉👉 Here We will Do Construction Injection
    TrainRepo trainRepo;
    TrainScheduleRepo trainScheduleRepo;
    ModelMapper modelMapper;

    public TrainScheduleService_Impl(TrainRepo trainRepo, TrainScheduleRepo trainScheduleRepo, ModelMapper modelMapper) {
        this.trainRepo = trainRepo;
        this.trainScheduleRepo = trainScheduleRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrainScheduleDto addTrainSchedule(TrainScheduleDto trainScheduleDto)
    {
        /// 👉👉 Check the Train is present or not
        Train train = trainRepo.findById(trainScheduleDto.getTrainid()).orElseThrow(() -> new NoSuchElementException("Train not found with ID: " + trainScheduleDto.getTrainid()));

        ///👉👉 Convert TrainScheduleDto to TrainSchedule entity
        TrainSchedule trainSchedule = modelMapper.map(trainScheduleDto, TrainSchedule.class);

        /// 👉👉 Set the train in TrainSchedule entity
        trainSchedule.setTrain(train);


        /// 👉👉 Save the TrainSchedule entity to the database
        trainScheduleRepo.save(trainSchedule);

        /// 👉👉 Convert the saved TrainSchedule entity to TrainScheduleDto
        TrainScheduleDto savedScheduleSto = modelMapper.map(trainSchedule , TrainScheduleDto.class);

        /// ❤️❤️❤️❤️❤️❤️❤️❤️❤️ when we are calling api it is not seting automatioc trainid
        savedScheduleSto.setTrainid(train.getId());

        return savedScheduleSto;
    }

    @Override
    public List<TrainScheduleDto> getTrainSchedulesByTrainId(Long trainId)
    {
        /// /👉👉👉👉 Here schedule are less so we Don't need to use pagination

        /// 👉👉 Fetch all TrainSchedules by trainId
        List<TrainSchedule> trainSchedules = trainScheduleRepo.findAll();



        /// 👉👉 Convert the list of TrainSchedule entities to a list of TrainScheduleDto
        return trainSchedules.stream()
                .map(trainSchedule -> {
                    TrainScheduleDto dto = modelMapper.map(trainSchedule, TrainScheduleDto.class);
                    dto.setTrainid(trainSchedule.getTrain().getId()); // ❤️ manually set trainid
                    return dto;
                })
                .toList();
    }

    @Override
    public void deleteTrainSchedule(Long trinScheduleid)
    {
        /// 👉👉 Check if the TrainSchedule exists
        TrainSchedule trainSchedule = trainScheduleRepo.findById(trinScheduleid).orElseThrow(()-> new NoSuchElementException("Train Schedule not found with ID: " + trinScheduleid));

        /// 👉👉 Delete the TrainSchedule
        trainScheduleRepo.delete(trainSchedule);
    }


    @Override
    public TrainScheduleDto updateTrainSchedule(Long trinScheduleid, TrainScheduleDto trainScheduleDto)
    {
        //👉👉 Check the Schedule is present or not
        TrainSchedule trainSchedule = trainScheduleRepo.findById(trinScheduleid).orElseThrow(() -> new NoSuchElementException("Train Schedule not found with ID: " + trinScheduleid));

        //👉👉 Check the Train is present or not
        Train train = trainRepo.findById(trainScheduleDto.getTrainid()).orElseThrow(() -> new NoSuchElementException("Train not found with ID: " + trainScheduleDto.getTrainid()));


        //👉👉 Update the TrainSchedule details
        trainSchedule.setTrain(train);
        trainSchedule.setAvaiableSeats(trainScheduleDto.getAvaiableSeats());
        trainSchedule.setRunDate(trainScheduleDto.getRunDate());

        //👉👉 Svae the updated TrainSchedule
        TrainSchedule savedTrainSchedule = trainScheduleRepo.save(trainSchedule);

        // 👉👉 Convert the saved TrainSchedule to TrainScheduleDto
        return modelMapper.map(savedTrainSchedule , TrainScheduleDto.class);

    }




}
