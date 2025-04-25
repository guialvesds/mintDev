package mint.dev.business.controller;

import mint.dev.business.services.PiggBankService;
import mint.dev.infrastructure.dto.WalletDTO;
import mint.dev.infrastructure.entity.PiggBankEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/piggbank")
public class PiggBankController {

    private PiggBankService piggBankService;

    public PiggBankController(PiggBankService piggBankService) {
        this.piggBankService = piggBankService;
    }


    @PostMapping("/{userId}")
    public ResponseEntity<PiggBankEntity> save(@RequestBody PiggBankEntity piggBankEntity, @PathVariable String userId) {
        var savePigg = piggBankService.createPiggBank(piggBankEntity, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePigg);
    }

    @GetMapping
    public ResponseEntity<List<PiggBankEntity>> findAll() {
        var piggbank = this.piggBankService.findAll();
        return ResponseEntity.ok(piggbank);
    }
}
