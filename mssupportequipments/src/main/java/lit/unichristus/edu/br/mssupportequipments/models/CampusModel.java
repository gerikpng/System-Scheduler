package lit.unichristus.edu.br.mssupportequipments.models;

import jakarta.persistence.Column;

public class CampusModel {
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
