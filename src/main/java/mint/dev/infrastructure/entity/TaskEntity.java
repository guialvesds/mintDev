package mint.dev.infrastructure.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("task")
public class TaskEntity {

    @Id
    private String id;
    private UserEntity user;
    private String title;
    private String description;
    private Boolean completed = Boolean.FALSE;

}
