package lit.unichristus.edu.br.mssupportequipments.services;

import jakarta.transaction.Transactional;
import lit.unichristus.edu.br.mssupportequipments.enums.SituationEnum;
import lit.unichristus.edu.br.mssupportequipments.models.SupportEquipmentModel;
import lit.unichristus.edu.br.mssupportequipments.repositories.SupportEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupportEquipmentService {


    @Autowired
    final SupportEquipmentRepository repository;

    public SupportEquipmentService(SupportEquipmentRepository repository) {
        this.repository = repository;
    }


    public List<SupportEquipmentModel> findAll(){
        return repository.findAll();
    }

    public List<SupportEquipmentModel> findByCampus(UUID id){
        return repository.findByCampusAndIsDeletedFalse(id);
    }

    @Transactional
    public Object save(SupportEquipmentModel supportequipmentModel){
        return repository.save(supportequipmentModel);
    }

    public boolean existByDescription(String description){
        return repository.existsByDescriptionAndIsDeletedFalse(description);
    }
    public Optional<SupportEquipmentModel> findById(UUID id){
        return repository.findById(id);
    }

    @Transactional
    public void delete(SupportEquipmentModel supportequipmentModel){
        repository.delete(supportequipmentModel);
    }


    public SupportEquipmentModel  getReleasableEquipament(UUID campus){
        SituationEnum relase = SituationEnum.InUse;
        return repository.findFirstByCampusAndSituation(campus,relase);
    }
    public Object getAllReleasableEquipament(UUID campus){
        SituationEnum relase = SituationEnum.InUse;
        return repository.findByCampusAndSituation(campus,relase);
    }

    public Object releaseEquipment(SupportEquipmentModel model) {
        return repository.save(model);
    }

    public Object releaseAllEquipment(List<SupportEquipmentModel> model) {
        return repository.saveAll(model);
    }

    public Object findByCampusAndSituation(UUID id, SituationEnum released) {
        return repository.findByCampusAndSituation(id,released);
    }

    public List<SupportEquipmentModel> getAllocatedEquipments(UUID idReserve) {
        return repository.findAllByReserveRoomId(idReserve);
    }
}
