package lit.unichristus.edu.br.mssupportequipment.service;

import jakarta.transaction.Transactional;
import lit.unichristus.edu.br.mssupportequipment.clients.CampusClient;
import lit.unichristus.edu.br.mssupportequipment.clients.SupportEquipmentsClient;
import lit.unichristus.edu.br.mssupportequipment.enums.SituationEnum;
import lit.unichristus.edu.br.mssupportequipment.models.CampusModel;
import lit.unichristus.edu.br.mssupportequipment.models.EquipmentCampusModel;
import lit.unichristus.edu.br.mssupportequipment.models.SupportEquipmentModel;
import lit.unichristus.edu.br.mssupportequipment.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupportEquipmentService {

    private final CampusClient campusClient;
    private final SupportEquipmentsClient equipmentClient;

    @Autowired
    final EquipmentRepository repository;

    public SupportEquipmentService(CampusClient campusClient, SupportEquipmentsClient equipmentClient, EquipmentRepository repository) {
        this.campusClient = campusClient;
        this.equipmentClient = equipmentClient;
        this.repository = repository;
    }


    public EquipmentCampusModel getEquipament(UUID id){
        ResponseEntity<CampusModel> reponseEntity = campusClient.getCampus(id);
        ResponseEntity<List<SupportEquipmentModel>> responseEquipment = equipmentClient.getByCampus(id);

        return EquipmentCampusModel
                .builder()
                .campus(reponseEntity.getBody())
                .equipments(responseEquipment.getBody())
                .build();


    }


    public SupportEquipmentModel  getReleasableEquipament(UUID campus){
        Date now = new Date();
        SituationEnum relase = SituationEnum.InUse;
        return repository.findFirstByCampusAndSituationAndBookedUntilBefore(campus,relase,now);
    }

    public List<SupportEquipmentModel> findAll(){
        return repository.findAll();
    }

    public List<SupportEquipmentModel> findByCampus(UUID id){
        return (List<SupportEquipmentModel>) repository.findByCampus(id);
    }

//    public List<SupportEquipmentModel> findBySituation(String situation){
//        return (List<SupportEquipmentModel>) repository.findBySituation(situation);
//    }

    @Transactional
    public Object save(SupportEquipmentModel supportequipmentModel){
        return repository.save(supportequipmentModel);
    }
    public boolean existByDescription(String description){
        return repository.existsByDescription(description);
    }
    public Optional<SupportEquipmentModel> findById(UUID id){
        return repository.findById(id);
    }

    @Transactional
    public void delete(SupportEquipmentModel supportequipmentModel){
        repository.delete(supportequipmentModel);
    }

    public Object releaseEquipment(SupportEquipmentModel model) {
        return repository.save(model);
    }
}
