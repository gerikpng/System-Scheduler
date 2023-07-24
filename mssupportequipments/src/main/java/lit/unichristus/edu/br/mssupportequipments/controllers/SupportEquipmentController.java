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
    public ResponseEntity<List<SupportEquipmentModel>> getAllEquipmentsByCampus(@PathVariable("id") UUID id){
        Object equipmentCampus = equipmentService.findByCampus(id);
        return ResponseEntity.ok((List<SupportEquipmentModel>) equipmentCampus);
    }

    @GetMapping(value="equipment-campus/released")
    public ResponseEntity<Object> getReleasedEquipmentsByCampus(@RequestParam("campus") UUID campusId){
        Object equipmentCampus = equipmentService.findByCampusAndSituation(campusId,SituationEnum.Released);
        return ResponseEntity.ok((List<SupportEquipmentModel>) equipmentCampus);
    }

    @GetMapping(value="equipment-campus/allocated")
    @ResponseBody
    public ResponseEntity<List<SupportEquipmentModel>> getAllocatedEquipment(@RequestParam("idReserve") UUID idReserve){
        List<SupportEquipmentModel> modelEquipments = equipmentService.getAllocatedEquipments(idReserve);
            return ResponseEntity.status(HttpStatus.OK).body(modelEquipments);
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


    //---FROM msReserve
    //funcao para reservar equipamento, devera definir BookedUntil de acordo com a hora final da reserva da sala, devera conter campo para vincular a reserva de sala
    @PutMapping("/reserve")
    public ResponseEntity<Object> reserveEquipment(@RequestParam(value="id") UUID id,@RequestParam(value="reserveRoom") UUID reserveRoomId){
        try{
            Optional<SupportEquipmentModel> optional = equipmentService.findById(id);
            if(!optional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EQUIPMENT NOT FOUND");
            }
            SupportEquipmentModel model = optional.get();
            model.setId(optional.get().getId());
            model.setSituation(SituationEnum.InUse);
            model.setReserveRoomId(reserveRoomId);
            return ResponseEntity.status(HttpStatus.OK).body(equipmentService.save(model));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }


    @PutMapping("/release")
    public ResponseEntity<Object> releaseManuallyEquipment(){
        try{
            UUID idCampus = UUID.fromString("25323fa5-3781-40b6-be77-a021ec4e82b1");
            List<SupportEquipmentModel> equipments = (List<SupportEquipmentModel>) equipmentService.getAllReleasableEquipament(idCampus);
            if(equipments.equals(null)) {
                System.out.println("NADA ENCONTRADO =---------------------------------------------------");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO ONE EQUIPMENT CAN BE RELEASED");
            }
            System.out.println("TUDO ENCONTRADO =---------------------------------------------------");

            for(SupportEquipmentModel equipamentos: equipments){
                equipamentos.setSituation(SituationEnum.Released);
            }
            return ResponseEntity.status(HttpStatus.OK).body(equipmentService.releaseAllEquipment(equipments));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }




//funcao que automaticamente busca uma lista de equipamentos com a situacao InUse que pertencem ao campus do usuário logado e altera a situacao para Released para todos aqueles que tenham ultrapassado o tempo de ocupacao definido.

    @PutMapping()
    @Scheduled(fixedDelay = 12, timeUnit = TimeUnit.HOURS)
    public ResponseEntity<Object> releaseAllEquipment(){
        try{
            UUID idCampus = UUID.fromString("25323fa5-3781-40b6-be77-a021ec4e82b1");
            List<SupportEquipmentModel> equipments = (List<SupportEquipmentModel>) equipmentService.getAllReleasableEquipament(idCampus);
            if(equipments.equals(null)) {
                System.out.println("NADA ENCONTRADO =---------------------------------------------------");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO ONE EQUIPMENT CAN BE RELEASED");
            }
            System.out.println("TUDO ENCONTRADO =---------------------------------------------------"+new Date());


            for(SupportEquipmentModel equipamentos: equipments){
                equipamentos.setSituation(SituationEnum.Released);
            }
            return ResponseEntity.status(HttpStatus.OK).body(equipmentService.releaseAllEquipment(equipments));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }



}
