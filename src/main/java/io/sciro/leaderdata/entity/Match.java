package io.sciro.leaderdata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.id.IdStrategy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * PROJECT   : leader-data
 * PACKAGE   : io.sciro.leaderdata.entity
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 16:27
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Data
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
    @JsonFormat(pattern = "MMM dd, yyyy, HH:mm:ss a")
    private Date timestamp;
    @JsonFormat(pattern = "MMM dd, yyyy, HH:mm:ss a")
    @LastModifiedDate
    private Date lastUpdated;
    @JsonFormat(pattern = "MMM dd, yyyy, HH:mm:ss a")
    @CreatedDate
    private Date created;


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
