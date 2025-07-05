package irctc_project.Controller.admin;

import irctc_project.Constants.AppConstants;
import irctc_project.Paiging.PagedResponce;
import irctc_project.Service.StationService;
import irctc_project.dto.StationDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//👉 @RestController :- For Rest API annotations

@RestController
@RequestMapping("/admin/stations")
public class StationController
{
    //👉 ResponseEntity<StationDto>  :- This ResponseEntity will help to indicate the HTTP Status
    //👉 @Valid :- This annotation is used Validate the StationDto
    //👉 @RequestBody :- This is used to take the data from API

    StationService stationService;


    //👉 This will do Automatic  Constructor Injection
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }


    //🔥 Add Stations

    @PostMapping
    public ResponseEntity<StationDto> addSation(@Valid @RequestBody StationDto stationDto)
    {
        StationDto dto = stationService.createStation(stationDto);

        return new ResponseEntity<>(dto , HttpStatus.CREATED);
    }


    //🔥 get All stations

    @GetMapping
   public PagedResponce<StationDto> getAllStations(

           @RequestParam(value = "page" , defaultValue = AppConstants.DEFAULT_PAGE) int page,
           @RequestParam(value = "size" , defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
           @RequestParam(value = "sortBy" , defaultValue = "name") String sortBy,
           @RequestParam(value = "sortDir" , defaultValue = "asc") String sortDir
   )
   {
        return  stationService.getAllStations(page,size,sortBy,sortDir);
   }

   //👉 @PathVariable: this is used to get parameter form the Path

    //🔥 get Stations By Id

    @GetMapping("/{id}")
    public ResponseEntity<StationDto> getStationByName (@PathVariable Long id)
    {
        StationDto stationDto = stationService.getStationById(id);

        return new ResponseEntity<>(stationDto , HttpStatus.OK);
    }


    //🔥 Update Station By Id

    @PutMapping("/{id}")
    public ResponseEntity<StationDto> updateStationByName (@PathVariable Long id ,
                                                           @Valid @RequestBody StationDto stationDto)
    {
        StationDto updatedStationDto = stationService.updateStations(id , stationDto);

        return new ResponseEntity<>(updatedStationDto , HttpStatus.OK);
    }

    //🔥 Delete Station By Id

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteStationByName (@PathVariable Long id )
    {
        stationService.deleteStations(id);

        return new ResponseEntity<>("Station Deleted Successfully" , HttpStatus.OK);
    }


}
