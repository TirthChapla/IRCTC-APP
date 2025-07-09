package irctc_project.Repository;

import irctc_project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,String >
{
    Optional<Role> findByRoleName(String role_name);

    boolean existsByRoleName(String role_name);
}
