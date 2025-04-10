package mint.dev.business.controller;

import mint.dev.business.exceptions.BusinessException;
import mint.dev.business.services.WalletService;
import mint.dev.infrastructure.dto.TransferRequestDTO;
import mint.dev.infrastructure.dto.WalletDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllWallets(){
        var wallets = this.walletService.findAll();
        return ResponseEntity.ok(wallets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDTO> findById(@PathVariable String id){
        var wallet = this.walletService.findById(id);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<WalletDTO> save(@RequestBody WalletDTO walletDTO, @PathVariable String userId) {
        var savedWallet = walletService.save(walletDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWallet);
    }

    // Build controi o objeto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id) {
        this.walletService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/add/{id}")
    public ResponseEntity<WalletDTO> addBalance(@PathVariable String id, @RequestBody WalletDTO walletDTO) {
        Double amountToAdd = walletDTO.getBalance();
        if (amountToAdd == null) {
            throw new BusinessException("O campo 'amountToAdd' é obrigatório.");
        }

        WalletDTO updatedWallet = walletService.addBalance(id, amountToAdd);
        return ResponseEntity.ok(updatedWallet);
    }

    @PatchMapping("/remove/{idWallet}")
    public ResponseEntity<WalletDTO> removeBalance(@PathVariable String idWallet, @RequestBody WalletDTO walletDTO){
        Double amountToRemove = walletDTO.getBalance();
        if (amountToRemove == null) {
            throw new BusinessException("O campo 'amountToRemove' é obrigatório.");
        }

        WalletDTO updateWallet = walletService.removeBalance(idWallet, amountToRemove);
        return ResponseEntity.ok(updateWallet);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferBalance(@RequestBody TransferRequestDTO transferRequest) {
        walletService.transferRequest(transferRequest);
        return ResponseEntity.ok("Transferência realizada com sucesso.");
    }

}
