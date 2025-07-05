package irctc_project.Service;

import irctc_project.Paiging.PagedResponce;
import irctc_project.dto.TrainDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface TrainService
{
    //We create this Interface Because we want loose coupling

    TrainDto createTrain(TrainDto trainDto);

    PagedResponce<TrainDto> getAllTrain(int page, int size, String sortBy, String sortDir);


    TrainDto getTrainById(Long trainId);

    void deleteTrainById(Long trainId);

    TrainDto updateTrainById(Long trainId, @Valid TrainDto trainDto);
}
