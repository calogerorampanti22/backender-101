package calogero.rampanti.backender_101.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
public class Person {

    @Id
    private UUID id;

    @NotBlank
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profession_id")
    private Profession profession;

    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name, @JsonProperty("profession") Profession profession) {
        this.id = id;
        this.name = name;
        this.profession = profession;
    }

    public Person(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

}
