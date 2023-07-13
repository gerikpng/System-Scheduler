package lit.unichristus.edu.br.mssupportequipment.models;

import jakarta.persistence.*;
import lit.unichristus.edu.br.mssupportequipment.enums.SituationEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "equipment")
public class SupportequipmentModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    public Date createdDate;

    public boolean isDeleted;
    @Serial
    private static final long serialVersionUID = 1L;
//    --------------------------

    @Column(nullable = false, length = 30)
    private String description;
    @Column(nullable = false, length = 30)
    private String brand;

    @Column(nullable = false, length = 30)
    private String productModel;

    @Column(nullable = false, length = 30)
    private String serialNumber;

    @Column(nullable = false, length = 30)
    private String patrimony;

    @Column(nullable = false, length = 30)
    private SituationEnum situation;

    @Column(nullable = true, length = 30)
    private Integer amount;

    @Column(nullable = false, length = 30)
    private Date lastChange;

    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "room_id")
    private RoomModel room;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "campus_id")
    private CampusModel campus;







}
