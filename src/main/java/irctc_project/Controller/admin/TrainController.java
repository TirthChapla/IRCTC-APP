package irctc_project.Controller.admin;

import irctc_project.Constants.AppConstants;
import irctc_project.Paiging.PagedResponce;
import irctc_project.Service.TrainService;
import irctc_project.dto.TrainDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/trains")
public class TrainController
{

    TrainService trainService;

    //👉 This will do Automatic Constructor Injection
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }


    //🔥 Add Train

    @PostMapping
    public ResponseEntity<TrainDto> addTrain(@Valid @RequestBody TrainDto trainDto)
    {
        TrainDto dto = trainService.createTrain(trainDto);

        return new ResponseEntity<>(dto , HttpStatus.CREATED);
    }



    //🔥 get All Trains

    @GetMapping
    public PagedResponce<TrainDto> getAllTrains(

            @RequestParam(value = "page" , defaultValue = AppConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size" , defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "sortBy" , defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir" , defaultValue = "asc") String sortDir

    )
    {
        return trainService.getAllTrain(page, size, sortBy, sortDir);
    }

    //🔥 get Train By Id

    @GetMapping("/{id}")
    public ResponseEntity<TrainDto> getTrainById(@PathVariable("id") Long trainId)
    {
        TrainDto trainDto = trainService.getTrainById(trainId);

        return new ResponseEntity<>(trainDto , HttpStatus.OK);
    }

    //🔥 Delete Train By Id

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainById(@PathVariable("id") Long trainId)
    {
        trainService.deleteTrainById(trainId);
        return new ResponseEntity<>("Train Deleted Successfully" , HttpStatus.OK);
    }

    //🔥 Update Train By Id

    @PutMapping("/{id}")
    public ResponseEntity<TrainDto> updateTrainById(@PathVariable("id") Long trainId,
                                                    @Valid @RequestBody TrainDto trainDto)
    {
        TrainDto updatedTrainDto = trainService.updateTrainById(trainId, trainDto);
        return new ResponseEntity<>(updatedTrainDto , HttpStatus.OK);
    }

}
