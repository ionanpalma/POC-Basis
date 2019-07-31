package lol.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lol.api.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Boolean existsByUsernameIgnoreCase(String username);

    Boolean existsByEmailIgnoreCase(String email);

    Optional<UserModel> findByUsernameOrEmailAllIgnoreCase(String username, String email);

}
