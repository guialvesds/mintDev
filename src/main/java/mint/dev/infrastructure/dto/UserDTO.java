package mint.dev.infrastructure.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String id;
    private String primaryName;
    private String secondName;
    private String email;

}
