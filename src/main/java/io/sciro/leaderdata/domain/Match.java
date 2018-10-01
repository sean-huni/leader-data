package io.sciro.leaderdata.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.id.IdStrategy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * PROJECT   : leader-data
 * PACKAGE   : io.sciro.leaderdata.domain
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 16:27
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@NodeEntity(label = "match")
@JsonIdentityInfo(generator = JSOGGenerator.class, property = "id")
public class Match implements IdStrategy {

    @Id
    @GeneratedValue
    private Long id;
    private String codeName;
    private Long round;
    private String me;
    private String pc;
    private Character result;
    private Date timestamp;
    @LastModifiedDate
    private Date lastUpdated;
    @CreatedDate
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Long getRound() {
        return round;
    }

    public void setRound(Long round) {
        this.round = round;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public Character getResult() {
        return result;
    }

    public void setResult(Character result) {
        this.result = result;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", codeName='" + codeName + '\'' +
                ", round=" + round +
                ", me='" + me + '\'' +
                ", pc='" + pc + '\'' +
                ", result=" + result +
                ", timestamp=" + timestamp +
                ", lastUpdated=" + lastUpdated +
                ", created=" + created +
                '}';
    }

    /**
     * Generates new id for given entity
     *
     * @param entity saved entity
     * @return identifier of the entity
     */
    @Override
    public Object generateId(Object entity) {
        return null;
    }
}
