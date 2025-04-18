package mint.dev.infrastructure.repository;

import mint.dev.infrastructure.entity.PiggBankEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PiggBankRespository extends MongoRepository<PiggBankEntity, String> {
}
