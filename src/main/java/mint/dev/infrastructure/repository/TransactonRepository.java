package mint.dev.infrastructure.repository;

import mint.dev.infrastructure.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactonRepository extends MongoRepository<TransactionEntity, String> {
}
