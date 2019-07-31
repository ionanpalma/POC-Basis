package lol.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "summoners")
public class SummonerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "summoner_id")
    private Long id;

    @Column(name = "summoner_name", nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_summoners",
            joinColumns = @JoinColumn(name = "summoner_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserModel> users;

    @OneToMany(mappedBy = "summoner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatchModel> matches;

    @OneToMany(mappedBy = "summoner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LeagueModel> leagues;

    @OneToMany(mappedBy = "summoner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MasteryModel> masteries;

    public SummonerModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}