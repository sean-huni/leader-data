package io.sciro.leaderdata.repo;

import io.sciro.leaderdata.entity.Match;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * PROJECT   : leader-data
 * PACKAGE   : io.sciro.leaderdata.repo
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 16:33
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
//@Transactional(propagation = Propagation.NESTED, value = "transactionManager")
@RepositoryRestResource(path = "matches", collectionResourceRel = "matches")
public interface MatchRepo extends Neo4jRepository<Match, Long> {

    Collection<Match> findAllByCodeName(@Param("codeName") String codeName);

    @Transactional
    Long deleteAllByCodeName(@Param("codeName") String codeName);
}
