package lit.unichristus.edu.br.mssupportequipments.repositories;

import lit.unichristus.edu.br.mssupportequipments.models.SupportEquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupportEquipmentRepository extends JpaRepository<SupportEquipmentModel, UUID> {
    public boolean existsByDescription(String description);

    List<SupportEquipmentModel> findByCampus(UUID id);

//    ResponseEntity<List<SupportEquipmentModel>> findByCampus(String campus);
}
