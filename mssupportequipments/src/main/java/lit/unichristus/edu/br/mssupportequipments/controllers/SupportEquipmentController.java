package lit.unichristus.edu.br.mssupportequipments.controllers;

import jakarta.validation.Valid;
import lit.unichristus.edu.br.mssupportequipments.dto.SupportEquipmentsDto;
import lit.unichristus.edu.br.mssupportequipments.enums.SituationEnum;
import lit.unichristus.edu.br.mssupportequipments.models.SupportEquipmentModel;
import lit.unichristus.edu.br.mssupportequipments.services.SupportEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/equipment")
@RequiredArgsConstructor
public class SupportEquipmentController {
    private final SupportEquipmentService equipmentService;



    @GetMapping
    public ResponseEntity<List<SupportEquipmentModel>> getSupportEquipments(){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.findAll());
    }

    @GetMapping("equipment-campus/{id}")
    public ResponseEntity<List<SupportEquipmentModel>> getByCampus(@PathVariable("id") UUID id){
        Object equipmentCampus = equipmentService.findByCampus(id);
        return ResponseEntity.ok((List<SupportEquipmentModel>) equipmentCampus);
    }


    @PostMapping
    public ResponseEntity<Object> saveSupportEquipment(@RequestBody @Valid SupportEquipmentsDto dto){
        try {
            if (equipmentService.existByDescription(dto.getDescription())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
            }
            SupportEquipmentModel model = dto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.save(model));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSupportEquipment(@PathVariable(value="id") UUID id){
        try{
            Optional<SupportEquipmentModel> optional = equipmentService.findById(id);
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
            Optional<SupportEquipmentModel> optional = equipmentService.findById(id);
            if(!optional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EQUIPMENT NOT FOUND");
            }
            if(!dto.getDescription().equals(optional.get().getDescription())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("NAME ALREADY IN USE");
            }
            SupportEquipmentModel model = dto.toModel();
            model.setId(optional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(equipmentService.save(model));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }


    //---FROM msRelease
    @PutMapping("/release")
    public ResponseEntity<Object> releaseManuallyEquipment(){
        try{
            UUID idCampus = UUID.fromString("25323fa5-3781-40b6-be77-a021ec4e82b1");
            SupportEquipmentModel equipments = equipmentService.getReleasableEquipament(idCampus);
            if(equipments.equals(null)) {
                System.out.println("NADA ENCONTRADO =---------------------------------------------------");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO ONE EQUIPMENT CAN BE RELEASED");
            }
            System.out.println("TUDO ENCONTRADO =---------------------------------------------------");

            equipments.setSituation(SituationEnum.Released);
            return ResponseEntity.status(HttpStatus.OK).body(equipmentService.releaseEquipment(equipments));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping()
    @Scheduled(fixedDelay = 12, timeUnit = TimeUnit.HOURS)
    public ResponseEntity<Object> releaseAllEquipment(){
        try{
            UUID idCampus = UUID.fromString("25323fa5-3781-40b6-be77-a021ec4e82b1");
            SupportEquipmentModel equipments = equipmentService.getReleasableEquipament(idCampus);



            if(equipments.equals(null)) {
                System.out.println("NADA ENCONTRADO =---------------------------------------------------");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO ONE EQUIPMENT CAN BE RELEASED");
            }
            System.out.println("TUDO ENCONTRADO =---------------------------------------------------"+new Date());


            equipments.setSituation(SituationEnum.Released);
            return ResponseEntity.status(HttpStatus.OK).body(equipmentService.releaseEquipment(equipments));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }



}
