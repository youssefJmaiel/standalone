package tn.group.standalone.ms.api;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;
import tn.group.standalone.ms.dto.PetTypeDto;
import tn.group.standalone.ms.dto.PetTypeRequest;
import tn.group.standalone.ms.service.PetTypeService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api")
@Validated
@AllArgsConstructor
@Timed
@CrossOrigin(origins = "*")
public class PetTypeController {

    private final PetTypeService petTypeService;

    @ApiOperation("Create new pet type")
    @Timed
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, path = "/v1/pet-types")
    ResponseEntity<PetTypeDto> create(@Valid @RequestBody PetTypeRequest petTypeRequest) {
        log.info("Create new pet type {}", petTypeRequest);
        PetTypeDto petTypeDto = petTypeService.create(petTypeRequest);
        final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/pet-types/{id}").build().expand(petTypeDto.getId()).toUri();
        return ResponseEntity.created(location).body(petTypeDto);
    }

    @ApiOperation("Find all pet types")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/pet-types")
    ResponseEntity<List<PetTypeDto>> getAll() {
        log.info("Find all pet types");
        List<PetTypeDto> petTypeDtos = petTypeService.getAllType();
        return ResponseEntity.ok().body(petTypeDtos);
    }

    @ApiOperation("Find pet type by id")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/petTypesId/{id}")
    ResponseEntity<PetTypeDto> getById(@PathVariable Long id) {
        log.info("Find pet type by id = {}", id);
        Optional<PetTypeDto> optionalPetType = petTypeService.getById(id);
        return optionalPetType.map(petType -> ResponseEntity.ok().body(petType)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation("Update pet type with id")
    @Timed
    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, path = "/v1/pet-typesUpdate/{id}")
    ResponseEntity<PetTypeDto> update(@Valid @RequestBody PetTypeRequest petTypeRequest, @PathVariable Long id) {
        log.info("Update pet type with id = {}", id);
        PetTypeDto petTypeDto = petTypeService.update(petTypeRequest, id);
        return ResponseEntity.ok().body(petTypeDto);
    }

    @ApiOperation("Delete pet type with id")
    @Timed
    @ApiIgnore
    @DeleteMapping(path = "/v1/pet-typesDelete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Delete pet type with id = {}", id);
        petTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
