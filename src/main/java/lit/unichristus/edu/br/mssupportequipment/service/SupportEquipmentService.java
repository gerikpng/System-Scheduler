package lit.unichristus.edu.br.mssupportequipment.service;

import jakarta.transaction.Transactional;
import lit.unichristus.edu.br.mssupportequipment.models.SupportequipmentModel;
import lit.unichristus.edu.br.mssupportequipment.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupportEquipmentService {

    @Autowired
    final EquipmentRepository repository;

    public SupportEquipmentService(EquipmentRepository repository) {
        this.repository = repository;
    }

    public List<SupportequipmentModel> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Object save(SupportequipmentModel supportequipmentModel){
        return repository.save(supportequipmentModel);
    }
    public boolean existByDescription(String description){
        return repository.existsByDescription(description);
    }
    public Optional<SupportequipmentModel> findById(UUID id){
        return repository.findById(id);
    }

    @Transactional
    public void delete(SupportequipmentModel supportequipmentModel){
        repository.delete(supportequipmentModel);
    }


}
