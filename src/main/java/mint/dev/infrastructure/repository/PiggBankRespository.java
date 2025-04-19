package mint.dev.infrastructure.repository;

import mint.dev.infrastructure.entity.PiggBankEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiggBankRespository extends MongoRepository<PiggBankEntity, String> {
}
