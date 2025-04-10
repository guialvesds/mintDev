package mint.dev.infrastructure.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TransferRequestDTO {
    private String senderUser;
    private String receiverUser;
    private Double amount;
}
