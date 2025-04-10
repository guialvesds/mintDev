package mint.dev.infrastructure.repository;

import mint.dev.infrastructure.entity.AddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<AddressEntity, String> {
}
