package io.sciro.leaderdata.projection;

import io.sciro.leaderdata.entity.Match;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * PROJECT   : leader-data
 * PACKAGE   : io.sciro.leaderdata.projection
 * USER      : sean
 * DATE      : 21-Wed-Nov-2018
 * TIME      : 00:03
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Projection(name = "inlineMatch", types = Match.class)
public interface InlineMatch {
   Long getId();
   String getCodeName();
   Long getRound();
   String getMe();
   String getPc();
   Character getResult();
   Date getTimestamp();
   Date getLastUpdated();
   Date getCreated();
}
