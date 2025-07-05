package irctc_project.Repository;

import irctc_project.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepo extends JpaRepository<Station , Long>
{

}
