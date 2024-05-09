package tn.group.standalone.ms.api;


import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.group.standalone.ms.dto.VetDto;
import tn.group.standalone.ms.dto.VetRequest;
import tn.group.standalone.ms.entity.Specialty;
import tn.group.standalone.ms.service.VetService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api")
@Validated
@AllArgsConstructor
@Timed
@CrossOrigin(origins = "*")
public class VetController {

    private final VetService vetService;

    @ApiOperation("Create new vet")
    @Timed
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, path = "/v1/vets")
    ResponseEntity<VetDto> create(@Valid @RequestBody VetRequest vetRequest) {
        log.info("Create new vet {}", vetRequest);
        VetDto vet = vetService.create(vetRequest);
        final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/vets/{id}").build().expand(vet.getId()).toUri();
        return ResponseEntity.created(location).body(vet);
    }

    @ApiOperation("Find all vets")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/FindAllVets")
    ResponseEntity<List<VetDto>> getAll() {
        log.info("Find all vets");
        List<VetDto> vets = vetService.getAll();
        return ResponseEntity.ok().body(vets);
    }
    @ApiOperation("Find all vets")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/vets/By")
    ResponseEntity<List<Specialty>> findSpecialityByVet(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String description) {
        log.info("Find all Speciality by vets");
        List<Specialty> vets = vetService.findSpecialiteByVet(firstName, lastName, description);
        return ResponseEntity.ok().body(vets);
    }
}
