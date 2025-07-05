package irctc_project.Service;

import irctc_project.dto.TrainScheduleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainScheduleService
{

    public TrainScheduleDto addTrainSchedule(TrainScheduleDto trainScheduleDto);

    public List<TrainScheduleDto> getTrainSchedulesByTrainId(Long trainId);

    public TrainScheduleDto updateTrainSchedule(Long trainId, TrainScheduleDto trainScheduleDto);

    public void deleteTrainSchedule(Long trainScheduleId);
}
