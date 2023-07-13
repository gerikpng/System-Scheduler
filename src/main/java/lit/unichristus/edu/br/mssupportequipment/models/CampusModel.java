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



}
