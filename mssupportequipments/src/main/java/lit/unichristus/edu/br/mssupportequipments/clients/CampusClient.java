package lit.unichristus.edu.br.mssupportequipments.clients;

import lit.unichristus.edu.br.mssupportequipments.models.CampusModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
@FeignClient(value = "coreservice", path = "/campus")
public interface CampusClient {

    @GetMapping("/{id}")
    ResponseEntity<CampusModel> getCampus(@PathVariable(value = "id") UUID id);
}
