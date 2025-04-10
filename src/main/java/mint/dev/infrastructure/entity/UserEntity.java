package mint.dev.infrastructure.entity;

import mint.dev.infrastructure.enums.PerfilUserStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity  {

    @Id
    private String id;
    private String primaryName;
    private String secondName;
    @Indexed(unique = true)
    private String email;

    private String password;
    private PerfilUserStatus perfil;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

}
