package mint.dev.business.services;


import mint.dev.infrastructure.repository.PiggBankRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PiggBankService {

    @Autowired
    private PiggBankRespository piggBankRespository;




}
