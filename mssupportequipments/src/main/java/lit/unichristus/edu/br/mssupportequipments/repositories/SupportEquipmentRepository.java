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
    SupportEquipmentModel findFirstByCampusAndSituation(UUID id, SituationEnum situation);
//    List<SupportEquipmentModel> findByCampusAndSituationAndBookedUntilBefore(UUID id, SituationEnum situation, Date now);

    Object findByCampusAndSituation(UUID id, SituationEnum released);

    List<SupportEquipmentModel> findAllByReserveRoomId(UUID idReserve);

    // SELECT PARA LISTAR OS EQUIPAMENTOS (de forma não duplicada) EM UM MENU SELECT
//    SELECT description	FROM public.support_equipment WHERE situation = 'Release' group by support_equipment.description ;
//    List<SupportEquipmentModel>


//    ResponseEntity<List<SupportEquipmentModel>> findByCampus(String campus);
}
