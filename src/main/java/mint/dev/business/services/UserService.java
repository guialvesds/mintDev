package mint.dev.business.services;

import mint.dev.infrastructure.enums.PerfilUserStatus;
import mint.dev.infrastructure.entity.UserEntity;
import mint.dev.infrastructure.repository.UserRepository;
import mint.dev.infrastructure.entity.WalletEntity;
import mint.dev.business.exceptions.BusinessException;
import mint.dev.infrastructure.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
  public UserService (UserRepository userRepository, PasswordEncoder passwordEncoder, WalletRepository walletRepository) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
      this.walletRepository = walletRepository;
  }

  public List<UserEntity> findAll(){
     return this.userRepository.findAll();
  }

  public UserEntity findId(String userId){
      return this.userRepository.findById(userId).orElseThrow(() -> new BusinessException("User not found"));
  }

  public mint.dev.infrastructure.entity.UserEntity findEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new BusinessException("User not found"));
    }

  public UserEntity save(UserEntity user){

      try {
          this.findEmail(user.getEmail());
          throw new BusinessException("Email already exists");

      } catch (BusinessException ex) {
          // Se a exceção for "User not found", significa que o email não existe
          if ("User not found".equals(ex.getMessage())) {
              user.setUpdatedAt(LocalDateTime.now());
              user.setPerfil(PerfilUserStatus.BASIC);
              user.setPassword(passwordEncoder.encode(user.getPassword()));
              UserEntity savedUser = userRepository.save(user);

              WalletEntity walletDefealt = new WalletEntity();
              walletDefealt.setName("Saldo");
              walletDefealt.setBalance(0.0);
              walletDefealt.setUser(savedUser);
              walletDefealt.setCreatedAt(LocalDateTime.now());

              walletRepository.save(walletDefealt);

              return savedUser;
          } else {
              throw ex;
          }
      }
  }

    public UserEntity update(UserEntity user) {
        UserEntity userEntity = findId(user.getId());
        userEntity.setUpdatedAt(LocalDateTime.now());

        if (user.getPrimaryName() != null) {
            userEntity.setPrimaryName(user.getPrimaryName());
        }
        if (user.getSecondName() != null) {
            userEntity.setSecondName(user.getSecondName());
        }
        if (user.getEmail() != null) {
            userEntity.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(user.getPerfil() != null) {
         userEntity.setPerfil(user.getPerfil());
        }
        return userRepository.save(userEntity);
    }

  public void delete(String userId){
       this.userRepository.deleteById(userId);
  }

}
