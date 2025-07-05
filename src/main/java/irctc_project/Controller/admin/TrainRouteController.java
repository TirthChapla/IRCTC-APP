package irctc_project.Controller.admin;

import irctc_project.Service.TrainRouteService;
import irctc_project.dto.TrainRouteDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-route")
public class TrainRouteController
{
    TrainRouteService trainRouteService;

    ////👉  Constructor-based dependency injection
    public TrainRouteController(TrainRouteService trainRouteService) {
        this.trainRouteService = trainRouteService;
    }


    ////👉 Add Train Route
    @PostMapping
    public TrainRouteDto addTrainRoute(@Valid @RequestBody TrainRouteDto trainRouteDto)
    {

        return trainRouteService.addTrainRoute(trainRouteDto);

    }

    /// /👉 Get TrainRoute List by Train ID
    @GetMapping("/train/{trainId}")
    public List<TrainRouteDto> getTrainRoutesByTrainId(@PathVariable Long trainId)
    {

        return trainRouteService.getTrainRoutesByTrainId(trainId);
    }

    /// /👉 Update Train Route

    @PutMapping("/{id}")
    public TrainRouteDto updateTrainRoute(@PathVariable Long id, @Valid @RequestBody TrainRouteDto trainRouteDto) {
        return trainRouteService.updateTrainRoute(id, trainRouteDto);
    }

    /// /👉 Delete Train Route
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainRoute(@PathVariable Long id) {
        trainRouteService.deleteTrainRoute(id);

        return new ResponseEntity<>("Train Route deleted successfully", HttpStatus.OK);
    }

}
