package lit.unichristus.edu.br.mssupportequipment.repository;

import lit.unichristus.edu.br.mssupportequipment.models.SupportequipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<SupportequipmentModel, UUID> {
    boolean existsByDescription(String description);
}
