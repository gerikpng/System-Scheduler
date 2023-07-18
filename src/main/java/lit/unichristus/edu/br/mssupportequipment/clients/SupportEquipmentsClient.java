package lit.unichristus.edu.br.mssupportequipment.clients;

import lit.unichristus.edu.br.mssupportequipment.models.SupportEquipmentModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "mssupportequipments", path = "/equipment")
public interface SupportEquipmentsClient {

    @GetMapping("equipment-campus/{id}")
    ResponseEntity<List<SupportEquipmentModel>> getByCampus(@PathVariable("id") UUID id);
}
