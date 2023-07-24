package lit.unichristus.edu.br.mssupportequipments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lit.unichristus.edu.br.mssupportequipments.enums.SituationEnum;
import lit.unichristus.edu.br.mssupportequipments.models.SupportEquipmentModel;

import java.util.Date;
import java.util.UUID;

public class SupportEquipmentsDto {

    private UUID id;

    @NotBlank(message="Name may not be blank")
    @Size(max = 30)
    private String description;
    private String brand;
    private boolean isDeleted;

    private String productModel;
    private String serialNumber;
    private String patrimony;
    @NotNull
    private SituationEnum situation;
    @NotNull
    private Integer amount;
    private Date lastChange;
    private UUID campus;
    private UUID reserveRoomId;

    public SupportEquipmentModel toModel(){
        return new SupportEquipmentModel(id,isDeleted,description,brand,productModel,serialNumber,patrimony,situation,amount,lastChange,campus,reserveRoomId);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
