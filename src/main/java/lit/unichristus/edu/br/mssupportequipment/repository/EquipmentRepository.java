package lit.unichristus.edu.br.mssupportequipment.repository;

import lit.unichristus.edu.br.mssupportequipment.enums.SituationEnum;
import lit.unichristus.edu.br.mssupportequipment.models.SupportEquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<SupportEquipmentModel, UUID> {

    Object findByCampus(UUID id);

    boolean existsByDescription(String description);

    SupportEquipmentModel findFirstByCampusAndSituationAndBookedUntilBefore(UUID id, SituationEnum situation, Date now);

}
