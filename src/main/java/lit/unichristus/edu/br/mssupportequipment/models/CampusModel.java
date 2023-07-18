package lit.unichristus.edu.br.mssupportequipment.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

public class CampusModel implements Serializable {
    private boolean isDeleted;
    @Column(nullable = false, length = 30)
    private String name;

    public CampusModel() {
    }

    public CampusModel(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
