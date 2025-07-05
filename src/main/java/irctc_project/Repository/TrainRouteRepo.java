package irctc_project.Repository;

import irctc_project.entity.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRouteRepo extends JpaRepository<TrainRoute , Long>
{
    @Query("SELECT tr FROM TrainRoute tr WHERE tr.train.id = :trainId ORDER BY tr.stationorder")
    List<TrainRoute> findByTrainId(Long trainId);
}
