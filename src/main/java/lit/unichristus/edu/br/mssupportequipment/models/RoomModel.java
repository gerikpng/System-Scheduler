package lit.unichristus.edu.br.mssupportequipment.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "room")
public class RoomModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

    public RoomModel() {
    }

    public RoomModel(String description, CampusModel campus, FloorModel floor, Integer capacity) {
        this.description = description;
        this.campus = campus;
        this.floor = floor;
        this.capacity = capacity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CampusModel getCampus() {
        return campus;
    }

    public void setCampus(CampusModel campus) {
        this.campus = campus;
    }

    public FloorModel getFloor() {
        return floor;
    }

    public void setFloor(FloorModel floor) {
        this.floor = floor;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
