package mint.dev.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {

    @Id
    private String id;
    private String userId;
    private String address;
    private Long number;
    private String district;
    private String complement;
    private String city;
    private String cep;

}
