package irctc_project.Repository;

import irctc_project.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepo extends JpaRepository<Train , Long>
{

}
