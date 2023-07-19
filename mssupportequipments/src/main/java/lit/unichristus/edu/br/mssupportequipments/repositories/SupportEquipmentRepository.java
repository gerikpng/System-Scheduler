package lit.unichristus.edu.br.mssupportequipments.repositories;

import lit.unichristus.edu.br.mssupportequipments.enums.SituationEnum;
import lit.unichristus.edu.br.mssupportequipments.models.SupportEquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface SupportEquipmentRepository extends JpaRepository<SupportEquipmentModel, UUID> {
    public boolean existsByDescriptionAndIsDeletedFalse(String description);

    List<SupportEquipmentModel> findByCampusAndIsDeletedFalse(UUID id);

    //from mReserve
    SupportEquipmentModel findFirstByCampusAndSituationAndBookedUntilBefore(UUID id, SituationEnum situation, Date now);

//    ResponseEntity<List<SupportEquipmentModel>> findByCampus(String campus);
}
