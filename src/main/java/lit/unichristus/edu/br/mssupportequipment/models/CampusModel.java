package lit.unichristus.edu.br.mssupportequipment.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "campus")
public class CampusModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    @Column(nullable = false, length = 30)
    private String name;

    public CampusModel() {
    }

    public CampusModel(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
