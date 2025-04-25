package mint.dev.business.controller;

import mint.dev.business.services.PiggBankService;
import mint.dev.infrastructure.entity.PiggBankEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/piggbank")
public class PiggBankController {

    private PiggBankService piggBankService;

    @GetMapping
    public ResponseEntity<List<PiggBankEntity>> findAll() {
        var piggbank = this.piggBankService.findAll();
        return ResponseEntity.ok(piggbank);
    }
}
