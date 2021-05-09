package org.sid.cenima.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @ToString @NoArgsConstructor @AllArgsConstructor
public class Film {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String realisateur;
    private Date dateSortie;
    private int duree;
    private String image;
    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Collection<Projection> projections;
    @ManyToOne
    private Categorie categorie;
}
