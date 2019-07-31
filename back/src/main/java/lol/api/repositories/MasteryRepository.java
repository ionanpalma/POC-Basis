package lol.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lol.api.model.MasteryModel;

@Repository
public interface MasteryRepository extends JpaRepository<MasteryModel, Long> {
}
