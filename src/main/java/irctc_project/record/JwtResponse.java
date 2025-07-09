package irctc_project.record;

import irctc_project.dto.UserDto;

public record JwtResponse(
        String token,
        String username

)
{
}
