package io.sciro.leaderdata.controller;

import io.sciro.leaderdata.domain.Match;
import io.sciro.leaderdata.repo.MatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * PROJECT   : leader-data
 * PACKAGE   : io.sciro.leaderdata.controller
 * USER      : sean
 * DATE      : 18-Tue-Sep-2018
 * TIME      : 19:53
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@RestController
@RequestMapping("/match")
public class MatchCtrl {
    private MatchRepo matchRepo;

    @Autowired
    public MatchCtrl(MatchRepo matchRepo) {
        this.matchRepo = matchRepo;
    }

    @GetMapping(value = "/")
    public Iterable<Match> findAllMatches() {
        return matchRepo.findAll();
    }


    @GetMapping(value = "/by-name/{name}")
    public Collection<Match> findAllMatchesByCodeName(@PathVariable("name") String name) {
        Collection<Match> matches = (Collection<Match>) matchRepo.findAll();
        return matches.stream().filter(match -> name.equalsIgnoreCase(match.getCodeName())).collect(Collectors.toList());
    }


    @GetMapping(value = "/{id}")
    public Match findAllMatchesById(@PathVariable("id") Long id) {
        Optional<Match> match = matchRepo.findById(id);
        return match.orElseGet(Match::new);
    }


    @PostMapping(path = "/")
    public Match saveNewMatch(@RequestBody Match match) {
        return matchRepo.save(match);
    }
}
