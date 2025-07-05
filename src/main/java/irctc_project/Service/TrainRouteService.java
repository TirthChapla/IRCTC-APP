package irctc_project.Service;

import irctc_project.dto.TrainRouteDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainRouteService
{
    public TrainRouteDto addTrainRoute(TrainRouteDto trainRouteDto);

    List<TrainRouteDto> getTrainRoutesByTrainId(Long trainId);

    TrainRouteDto updateTrainRoute(Long id, @Valid TrainRouteDto trainRouteDto);

    void deleteTrainRoute(Long id);
}
