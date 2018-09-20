package io.sciro.leaderdata.repo;

import io.sciro.leaderdata.domain.Match;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * PROJECT   : leader-data
 * PACKAGE   : io.sciro.leaderdata.repo
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 16:33
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Repository
public interface MatchRepo extends Neo4jRepository<Match, Long> {
}
