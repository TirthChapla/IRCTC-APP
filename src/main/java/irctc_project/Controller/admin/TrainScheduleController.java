package irctc_project.Controller.admin;

import irctc_project.Service.TrainScheduleService;
import irctc_project.dto.TrainRouteDto;
import irctc_project.dto.TrainScheduleDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-schedule")
public class TrainScheduleController
{

    ///👉👉 We autowired Using Construction Injection
    TrainScheduleService trainScheduleService;

    public TrainScheduleController(TrainScheduleService trainScheduleService) {
        this.trainScheduleService = trainScheduleService;
    }



    @PostMapping
    public TrainScheduleDto  addTrainSchedules( @Valid @RequestBody TrainScheduleDto trainScheduleDto)
    {
        /// 👉👉 I saved TrainSchedule to the DB
        TrainScheduleDto savedSedule = trainScheduleService.addTrainSchedule(trainScheduleDto);

        return savedSedule;
    }


    @GetMapping("train/{trainId}")
    public List<TrainScheduleDto> getTrainSchedulesByTrainId( @PathVariable Long trainId)
    {
        /// 👉👉 Fetch All the Shedule By passing Train ID
        List<TrainScheduleDto> allSchedules = trainScheduleService.getTrainSchedulesByTrainId(trainId);

        return allSchedules;
    }


    @DeleteMapping("{trainScheduleiId}")
    public String deleteTrainSchedule(@PathVariable Long trainScheduleiId)
    {
        ///👉👉 We will Pass trainScheduleiId to delete schedule
        trainScheduleService.deleteTrainSchedule(trainScheduleiId);

        return "Train-Schedule Deleted Success fully = " + trainScheduleiId ;
    }


    @PutMapping("{trainScheduleiId}")
    public TrainScheduleDto updateTrainSchedule(@PathVariable Long trainScheduleiId,
                                                @RequestBody TrainScheduleDto trainScheduleDto)
    {
        ///👉👉 We are passing trinScheduleid n TrinScheduleid object to update
        TrainScheduleDto upadtedTrainScheduleDto = trainScheduleService.updateTrainSchedule(trainScheduleiId,trainScheduleDto);

        upadtedTrainScheduleDto.setTrainid(trainScheduleDto.getTrainid());
        return upadtedTrainScheduleDto;

    }


}
