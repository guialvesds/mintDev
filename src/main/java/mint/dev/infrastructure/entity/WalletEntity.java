package mint.dev.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("wallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletEntity {

    @Id
    private String id;
    private String name;
    private Double balance = 00.00;
    private UserEntity user;
    private LocalDateTime createdAt = LocalDateTime.now();

}
