package lit.unichristus.edu.br.mssupportequipments.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lit.unichristus.edu.br.mssupportequipments.enums.SituationEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "support_equipment")
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class SupportEquipmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Serial
    private static final long serialVersionUID = 1L;

    private Date createdDate;

    private boolean isDeleted;
//    --------------------------

    @Column(nullable = false, length = 30)
    private String description;
    @Column(nullable = true, length = 30)
    private String brand;

    @Column(nullable = true, length = 30)
    private String productModel;

    @Column(nullable = true, length = 30)
    private String serialNumber;

    @Column(nullable = true, length = 30)
    private String patrimony;

    @Enumerated(EnumType.STRING)
    private SituationEnum situation;

    private Integer amount;

    private Date lastChange;

    private UUID campus;

    private UUID reserveRoomId;

    public SupportEquipmentModel(UUID id, boolean isDeleted, String description, String brand, String productModel, String serialNumber, String patrimony, SituationEnum situation, Integer amount, Date lastChange,UUID campus, UUID reserveRoomId ) {
        this.isDeleted = isDeleted;
        this.description = description;
        this.brand = brand;
        this.productModel = productModel;
        this.serialNumber = serialNumber;
        this.patrimony = patrimony;
        this.situation = situation;
        this.amount = amount;
        this.lastChange = lastChange;
        this.campus = campus;
        this.reserveRoomId = reserveRoomId;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPatrimony() {
        return patrimony;
    }

    public void setPatrimony(String patrimony) {
        this.patrimony = patrimony;
    }

    public SituationEnum getSituation() {
        return situation;
    }

    public void setSituation(SituationEnum situation) {
        this.situation = situation;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public UUID getCampus() {
        return campus;
    }

    public void setCampus(UUID campus) {
        this.campus = campus;
    }

    public UUID getReserveRoomId() {
        return reserveRoomId;
    }

    public void setReserveRoomId(UUID reserveRoomId) {
        this.reserveRoomId = reserveRoomId;
    }
}
