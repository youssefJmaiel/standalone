package tn.group.standalone.ms.api;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.group.standalone.ms.dto.PetDto;
import tn.group.standalone.ms.dto.PetRequest;
import tn.group.standalone.ms.service.PetService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
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
public class PetController {

    private final PetService petService;

    @ApiOperation("Find all pets by criteria")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/pets")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    public ResponseEntity<PagedModel<EntityModel<PetDto>>> search(@RequestParam(value = "pet_name", required = false) String petName,
                                                                  @RequestParam(value = "pet_types", required = false) List<Long> petTypeIds,
                                                                  @RequestParam(value = "owners", required = false) List<Long> ownerIds,
                                                                  @RequestParam(value = "min_date_visit", required = false) LocalDate minDateVisit,
                                                                  @RequestParam(value = "max_date_visit", required = false) LocalDate maxDateVisit,
                                                                  Pageable pageable, PagedResourcesAssembler<PetDto> assembler) {
        log.info("Find all pets by petName = {}, petTypeId = {}, ownerId = {}, minDateVisit = {}, maxDateVisit = {}", petName, petTypeIds, ownerIds, minDateVisit, maxDateVisit);
        final Page<PetDto> pets = petService.search(pageable, petName, petTypeIds, ownerIds, minDateVisit, maxDateVisit);
        PagedModel<EntityModel<PetDto>> pagedPets = assembler.toModel(pets);
        return ResponseEntity.ok(pagedPets);

    }

    @ApiOperation("Find pet with id")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/pets/{id}")
    public ResponseEntity<PetDto> getById(@PathVariable Long id) {
        log.info("Find pet with id = {}", id);
        Optional<PetDto> petDto = petService.getById(id);
        return petDto.map(pet -> ResponseEntity.ok().body(pet)).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @ApiOperation("Create new pet")
    @Timed
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, path = "/v1/createPet")
    public ResponseEntity<PetDto> create(@Valid @RequestBody PetRequest petRequest) {
        log.info("Create new pet {}", petRequest);
        PetDto petDto = petService.create(petRequest);
        final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/pets/{id}").build().expand(petDto.getId()).toUri();
        return ResponseEntity.created(location).body(petDto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/petsByOwners")
    public ResponseEntity<List<PetDto>> getPetOwners(@RequestBody String city) {
        log.info("Find owner by city = {}", city);
        List<PetDto> petDtos =  petService.getPerrByOwner(city);
        return ResponseEntity.ok(petDtos);
    }
    @GetMapping("/v1/petsOwnercity")
    public ResponseEntity<List<PetDto>> getByCity(@RequestParam String city){
        List<PetDto> petDtos = petService.getPetsByOwners(city);
        return ResponseEntity.ok(petDtos);
    }
    @GetMapping("/v1/petsOwnercity2")
    public ResponseEntity<List<PetDto>> getByCity2(@RequestParam String city){
        List<PetDto> petDtos = petService.getPetsByOwners2(city);
        return ResponseEntity.ok(petDtos);
    }
    @Timed
    @GetMapping("/v1/pets/by-visit")
    public ResponseEntity<List<PetDto>> getPetByVisits(@RequestParam(name = "type-visit") String typeVisit){
        List<PetDto> petDtosVet = petService.findByVisits(typeVisit);
        return ResponseEntity.ok(petDtosVet);
    }
}
