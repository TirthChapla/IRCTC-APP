package irctc_project;

import irctc_project.Repository.RoleRepo;
import irctc_project.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IrctcProjectApplication implements CommandLineRunner {

	@Autowired
	RoleRepo roleRepo;

	public static void main(String[] args)
	{
		SpringApplication.run(IrctcProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		if(!roleRepo.existsByRoleName("ROLE_ADMIN"))
		{
			Role adminRole = new Role();
			adminRole.setId("admin");
			adminRole.setRoleName("ROLE_ADMIN");
			roleRepo.save(adminRole);

			System.out.println("ROLE_ADMIN added to the Role table");
		}

		if(!roleRepo.existsByRoleName("ROLE_USER"))
		{
			Role userRole = new Role();
			userRole.setId("user");
			userRole.setRoleName("ROLE_USER");
			roleRepo.save(userRole);

			System.out.println("ROLE_USER added to the Role table");
		}

	}
}
