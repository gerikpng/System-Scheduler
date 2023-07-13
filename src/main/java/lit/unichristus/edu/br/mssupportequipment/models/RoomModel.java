package lit.unichristus.edu.br.mssupportequipment.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "room")
public class RoomModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column(nullable = false, length = 30)
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "campus_id")
    private CampusModel campus;

    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "floor_id")
    private FloorModel floor;

    @Column(nullable = true, length = 30)
    private Integer capacity;




}
