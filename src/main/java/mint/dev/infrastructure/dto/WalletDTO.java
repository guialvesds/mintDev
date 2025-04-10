package mint.dev.infrastructure.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WalletDTO {

    private String id;
    private String name;
    private Double balance;
    private LocalDateTime createdAt;
    private String userId;
    private String userPrimaryName;
    private String userSecondName;
    private String userEmail;


}
