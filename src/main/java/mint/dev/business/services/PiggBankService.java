package mint.dev.business.services;


import mint.dev.business.exceptions.BusinessException;
import mint.dev.infrastructure.entity.PiggBankEntity;
import mint.dev.infrastructure.entity.UserEntity;
import mint.dev.infrastructure.repository.PiggBankRespository;
import mint.dev.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiggBankService {

    @Autowired
    private PiggBankRespository piggBankRespository;

@Autowired
    private UserRepository userRepository;

private BusinessException piggBankNotFound(){
    return new BusinessException("Cofrinho not found!");
}

    public PiggBankService(PiggBankRespository piggBankRespository, UserRepository userRepository) {
        this.piggBankRespository = piggBankRespository;
        this.userRepository = userRepository;
    }

    public List<PiggBankEntity> findAll() {
      return this.piggBankRespository.findAll();
    }

    public PiggBankEntity getById(String piggId) {
        return this.piggBankRespository.findById(piggId).orElseThrow(() -> this.piggBankNotFound());
    }

    public PiggBankEntity createPiggBank(PiggBankEntity piggBankEntity, String userId){
        PiggBankEntity piggBank = piggBankEntity;

        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new BusinessException("User not found!"));

        piggBank.setUser(userEntity);

        PiggBankEntity saved = this.piggBankRespository.save(piggBank);

        return saved;

    }


}
