package lit.unichristus.edu.br.mssupportequipment.controllers;

import jakarta.validation.Valid;
import lit.unichristus.edu.br.mssupportequipment.dto.SupportEquipmentsDto;
import lit.unichristus.edu.br.mssupportequipment.models.SupportequipmentModel;
import lit.unichristus.edu.br.mssupportequipment.repository.EquipmentRepository;
import lit.unichristus.edu.br.mssupportequipment.service.SupportEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    final SupportEquipmentService equipmentService;
    @Autowired
    final EquipmentRepository equipmentRepository;

    public EquipmentController(SupportEquipmentService equipmentService, EquipmentRepository equipmentRepository) {
        this.equipmentService = equipmentService;
        this.equipmentRepository = equipmentRepository;
    }


    @GetMapping
    public ResponseEntity<List<SupportequipmentModel>> getSupportEquipments(){
           return ResponseEntity.status(HttpStatus.OK).body(equipmentService.findAll());
    }
    @PostMapping
    public ResponseEntity<Object> saveSupportEquipment(@RequestBody @Valid SupportEquipmentsDto dto){
        try {
            if (equipmentService.existByDescription(dto.getDescription())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
            }
            SupportequipmentModel model = dto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(equipmentRepository.save(model));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSupportEquipment(@PathVariable(value="id") UUID id){
        try{
            Optional<SupportequipmentModel> optional = equipmentService.findById(id);
            if(!optional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EQUIPMENT NOT FOUND");
            }
            equipmentService.delete(optional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Equipment deleted successfully.");
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> editSupportEquipment(@PathVariable(value="id") UUID id, @RequestBody @Valid SupportEquipmentsDto dto){
        try{
            Optional<SupportequipmentModel> optional = equipmentService.findById(id);
            if(!optional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EQUIPMENT NOT FOUND");
            }
            if(!dto.getDescription().equals(optional.get().getDescription())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("NAME ALREADY IN USE");
            }
            SupportequipmentModel model = dto.toModel();
            model.setId(optional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(equipmentService.save(model));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }


}
