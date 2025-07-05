package irctc_project.Repository;

import irctc_project.entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainScheduleRepo extends JpaRepository<TrainSchedule , Long>
{

    @Query("SELECT ts FROM TrainSchedule ts WHERE ts.train.id = ?1 ")
    public List<TrainSchedule> findByTrainId(Long trainId);


}
