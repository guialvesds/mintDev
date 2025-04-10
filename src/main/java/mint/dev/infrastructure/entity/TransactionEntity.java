package mint.dev.infrastructure.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("transactions")
@Data
@Getter
@Setter
public class TransactionEntity {

    @Id
    private String id;
    private String senderUserId;
    private String receiverUserId;
    private double amount;
    private LocalDateTime createdAt;

}
