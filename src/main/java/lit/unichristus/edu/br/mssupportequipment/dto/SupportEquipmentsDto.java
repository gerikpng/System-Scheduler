package lit.unichristus.edu.br.mssupportequipment.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lit.unichristus.edu.br.mssupportequipment.enums.SituationEnum;
import lit.unichristus.edu.br.mssupportequipment.models.CampusModel;
import lit.unichristus.edu.br.mssupportequipment.models.RoomModel;
import lit.unichristus.edu.br.mssupportequipment.models.SupportequipmentModel;

import java.util.Date;
import java.util.UUID;

public class SupportEquipmentsDto {

    @NotBlank(message="Name may not be blank")
    @Size(max = 30)
    private String description;
    @NotBlank
    private String brand;
    @NotBlank
    private String productModel;
    @NotBlank
    private String serialNumber;
    @NotBlank
    private String patrimony;
    @NotNull
    private SituationEnum situation;
    @NotNull
    private Integer amount;
    @NotNull
    private Date lastChange;
    @NotNull
    private RoomModel room;
    @NotNull
    private CampusModel campus;

    public SupportequipmentModel toModel(){
        return new SupportequipmentModel(description,brand,productModel,serialNumber,patrimony,situation,amount,lastChange,room,campus);
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

    public RoomModel getRoom() {
        return room;
    }

    public void setRoom(RoomModel room) {
        this.room = room;
    }

    public CampusModel getCampus() {
        return campus;
    }

    public void setCampus(CampusModel campus) {
        this.campus = campus;
    }
}
