package mint.dev.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("piggbank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PiggBankEntity {

    @Id
    private String id;
    private Double balance = 00.00;
    private String name = "Cofrinho";
    private Double goal = 00.00;
    private Double goalPerc = 00.00;
    private LocalDateTime createdAt = LocalDateTime.now();
    private UserEntity user;
}
