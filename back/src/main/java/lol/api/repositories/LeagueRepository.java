package lol.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lol.api.model.LeagueModel;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueModel, Long> {
}
