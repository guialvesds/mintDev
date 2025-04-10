package mint.dev.business.services;

import mint.dev.business.exceptions.BusinessException;
import mint.dev.infrastructure.dto.TransferRequestDTO;
import mint.dev.infrastructure.dto.WalletDTO;
import mint.dev.infrastructure.entity.TransactionEntity;
import mint.dev.infrastructure.entity.UserEntity;
import mint.dev.infrastructure.entity.WalletEntity;
import mint.dev.infrastructure.repository.TransactonRepository;
import mint.dev.infrastructure.repository.UserRepository;
import mint.dev.infrastructure.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactonRepository transactonRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, UserRepository userRepository, TransactonRepository transactonRepository) {

        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.transactonRepository = transactonRepository;
    }

    public BusinessException exceptionWalletNotFound() {
        return new BusinessException("Wallet not found");
    }

    // Aqui converto WalletEntity para WalletDTO
    private WalletDTO toDTO(WalletEntity walletEntity) {
        WalletDTO walletDTO = new WalletDTO();

        walletDTO.setId(walletEntity.getId());
        walletDTO.setName(walletEntity.getName());
        walletDTO.setBalance(walletEntity.getBalance());
        walletDTO.setCreatedAt(walletEntity.getCreatedAt());

        if(walletEntity.getUser() != null) {
            walletDTO.setUserId(walletEntity.getUser().getId());
            walletDTO.setUserPrimaryName(walletEntity.getUser().getPrimaryName());
            walletDTO.setUserSecondName(walletEntity.getUser().getSecondName());
            walletDTO.setUserEmail(walletEntity.getUser().getEmail());

        }

        return walletDTO;
    }

    // Agora converto WalletDTO para WalletEntity

    private WalletEntity toEntity(WalletDTO walletDTO){
        WalletEntity walletEntity = new WalletEntity();

        walletEntity.setId(walletDTO.getId());
        walletEntity.setName(walletDTO.getName());
        walletEntity.setBalance(walletDTO.getBalance());
        walletEntity.setCreatedAt(walletDTO.getCreatedAt());
        // Aqui não passo o userID pois irei buscar no UserEntity no repo

        return walletEntity;

    }

    public List<WalletDTO> findAll(){
        return walletRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public WalletDTO findById(String id){
      WalletEntity walletEntity =  this.walletRepository.findById(id).orElseThrow(() -> new BusinessException("Wallet not found"));

        return toDTO(walletEntity);
    }


    public WalletDTO save(WalletDTO walletDTO, String userId) {
        WalletEntity walletEntity = toEntity(walletDTO);

        // Aqui busca o UserEntity pelo userId
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> exceptionWalletNotFound());

        walletEntity.setUser(userEntity);

        WalletEntity savedWallet = walletRepository.save(walletEntity);

        return toDTO(savedWallet);
    }

    public WalletDTO addBalance(String walletId, double amountToAdd) {
        // Busca a carteira pelo ID
        WalletEntity walletEntity = walletRepository.findById(walletId)
                .orElseThrow(() -> exceptionWalletNotFound());

        // Atualiza o saldo
        double newBalance = walletEntity.getBalance() + amountToAdd;
        walletEntity.setBalance(newBalance);

        // Salva a carteira atualizada
        WalletEntity savedWallet = walletRepository.save(walletEntity);

        // Converte para DTO e retorna
        return toDTO(savedWallet);
    }

    public WalletDTO removeBalance(String walletId, double amountRemove){
        WalletEntity walletEntity = walletRepository.findById(walletId)
                .orElseThrow(() -> new BusinessException("Wallet not found"));

        double newBalance = walletEntity.getBalance() - amountRemove;
        walletEntity.setBalance(newBalance);

        WalletEntity saveWallet = walletRepository.save(walletEntity);

        return toDTO(saveWallet);
    }

    public void delete(String userId){
        this.walletRepository.deleteById(userId);
    }


public void transferRequest(TransferRequestDTO transfer){

    WalletEntity senderUser = walletRepository.findByUserIdAndName(transfer.getSenderUser(), "Saldo")
            .orElseThrow(() -> new BusinessException("Carteira remetente não encontado."));

    WalletEntity receiverUser = walletRepository.findByUserIdAndName(transfer.getReceiverUser(), "Saldo")
            .orElseThrow(() -> new BusinessException("Carteria destinatário não encontrada."));

    if(senderUser.getBalance() < transfer.getAmount()) {
        throw new BusinessException("Saldo insuficiente!");
    } else {
        senderUser.setBalance(senderUser.getBalance() - transfer.getAmount());
        receiverUser.setBalance(receiverUser.getBalance() + transfer.getAmount());

        walletRepository.save(senderUser);
        walletRepository.save(receiverUser);

        saveTransaction(senderUser.getId(), receiverUser.getId(), transfer.getAmount());
    }
}

private void saveTransaction(String senderUserId, String receiverUserId, double amount){
    TransactionEntity transaction = new TransactionEntity();

    transaction.setSenderUserId(senderUserId);
    transaction.setReceiverUserId(receiverUserId);
    transaction.setAmount(amount);
    transaction.setCreatedAt(LocalDateTime.now());

    transactonRepository.save(transaction);
}

}
