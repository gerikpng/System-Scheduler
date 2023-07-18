package lit.unichristus.edu.br.mssupportequipment.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EquipmentCampusModel {
    private CampusModel campus;
    private List<SupportEquipmentModel> equipments;

}
