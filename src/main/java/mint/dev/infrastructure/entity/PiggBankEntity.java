package mint.dev.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("piggbank")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class PiggBankEntity {

    @Id
    private String id;
    private Double balance;
    private String name;
    private Double goal;
    private Double goalPerc;
    private LocalDateTime createdAt = LocalDateTime.now();
    private UserEntity user;
}
