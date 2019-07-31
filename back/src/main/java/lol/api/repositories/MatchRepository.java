package lol.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lol.api.model.MatchModel;

@Repository
public interface MatchRepository extends JpaRepository<MatchModel, Long> {
}
