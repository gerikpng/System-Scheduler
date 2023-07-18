package lit.unichristus.edu.br.mssupportequipment.controllers;

import jakarta.validation.Valid;
import lit.unichristus.edu.br.mssupportequipment.dto.SupportEquipmentsDto;
import lit.unichristus.edu.br.mssupportequipment.enums.SituationEnum;
import lit.unichristus.edu.br.mssupportequipment.models.EquipmentCampusModel;
import lit.unichristus.edu.br.mssupportequipment.models.SupportEquipmentModel;
import lit.unichristus.edu.br.mssupportequipment.service.SupportEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reserve-equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final SupportEquipmentService equipmentService;

    @GetMapping(value = "equipment-campus",params = "id")
    public ResponseEntity<EquipmentCampusModel> getEquipments(@RequestParam("id") UUID id){
        EquipmentCampusModel equipmentCampus = equipmentService.getEquipament(id);

        return ResponseEntity.ok(equipmentCampus);
    }


    @GetMapping(value = "release")
    public ResponseEntity<SupportEquipmentModel> getNextRelease(){
        UUID idCampus = UUID.fromString("25323fa5-3781-40b6-be77-a021ec4e82b1");
        SupportEquipmentModel equipmentCampus = equipmentService.getReleasableEquipament(idCampus);
        equipmentCampus.setSituation(SituationEnum.valueOf("Released"));

        return ResponseEntity.ok(equipmentCampus);
    }



//    @PutMapping("/{id}")
//    public ResponseEntity<Object> reserveEquipment(@PathVariable(value="id") UUID id, @RequestBody @Valid SupportEquipmentsDto dto){
//        try{
//            Optional<SupportEquipmentModel> optional = equipmentService.findById(id);
//            if(!optional.isPresent()){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EQUIPMENT NOT FOUND");
//            }
//            if(dto.getDescription().equals(optional.get().getDescription())){
//                return ResponseEntity.status(HttpStatus.CONFLICT).body("NAME ALREADY IN USE");
//            }
//            SupportEquipmentModel model = dto.toModel();
//            model.setId(optional.get().getId());
//            return ResponseEntity.status(HttpStatus.OK).body(equipmentService.save(model));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//        }
//    }



    //FUNÇAO COM ANOTACAO SCHEDULED QUE REALIZA VERIFICAÇÕES CONSTANTES EM DETERMINADO PERÍODO DE TEMPO PARA VERIFICAR SE DETERMINADO OBJETO JA DEVERÁ SER LIBERADO APÓS O TERMINO DA AULA OU NÃO
//    @PutMapping()
//    @Scheduled(fixedDelay = 1)
//    public ResponseEntity<Object> releaseEquipment(){
//        try{
//            UUID idEquipment = UUID.fromString("882a3c4f-2087-495d-b1c5-3c0f3062c388");
//            Optional<SupportEquipmentModel> findEquipment = equipmentService.findById(idEquipment);
//            if(!findEquipment.isPresent()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EQUIPMENT NOT FOUND");
//            }
//            SupportEquipmentModel model = findEquipment.get();
//            model.setId(findEquipment.get().getId());
//            model.setSituation(SituationEnum.valueOf("Released"));
//            return ResponseEntity.status(HttpStatus.OK).body(equipmentService.releaseEquipment(model));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//        }
//    }



    ///---------------------------------------------------------------------------------------------------------------
    @PutMapping()
    @Scheduled(fixedDelay = 100000)
    public ResponseEntity<Object> releaseAllEquipment(){
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
//








//    @GetMapping
//    public ResponseEntity<List<SupportEquipmentModel>> getSupportEquipments(){
//           return ResponseEntity.status(HttpStatus.OK).body(equipmentService.findAll());

//    }
//


//    @PostMapping
//    public ResponseEntity<Object> saveSupportEquipment(@RequestBody @Valid SupportEquipmentsDto dto){
//        try {
//            if (equipmentService.existByDescription(dto.getDescription())) {
//                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
//            }
//            SupportEquipmentModel model = dto.toModel();
//            return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.save(model));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteSupportEquipment(@PathVariable(value="id") UUID id){
//        try{
//            Optional<SupportEquipmentModel> optional = equipmentService.findById(id);
//            if(!optional.isPresent()){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EQUIPMENT NOT FOUND");
//            }
//            equipmentService.delete(optional.get());
//            return ResponseEntity.status(HttpStatus.OK).body("Equipment deleted successfully.");
//        }catch (Exception e){
//            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//        }
//    }



}
