package tn.group.standalone.ms.api;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.group.standalone.ms.dto.OwnerDto;
import tn.group.standalone.ms.dto.OwnerRequest;
import tn.group.standalone.ms.service.OwnerService;

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
public class OwnerController {

    private final OwnerService ownerService;

    @ApiOperation("Create new owner")
    @Timed
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, path = "/v1/createOwners")
    public ResponseEntity<OwnerDto> create(@Valid @RequestBody OwnerRequest ownerRequest) {
        log.info("Create new owner");
        OwnerDto ownerDto = ownerService.create(ownerRequest);
        final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/owners/{id}").build().expand(ownerDto.getId()).toUri();
        return ResponseEntity.created(location).body(ownerDto);
    }

    @ApiOperation("Find all owners")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/owners")
    public ResponseEntity<List<OwnerDto>> getAll() {
        log.info("Find all owners");
        List<OwnerDto> ownerList = ownerService.findAll();
        return ResponseEntity.ok(ownerList);
    }

    @ApiOperation("Find all owners with name")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/owners/by-name")
    public ResponseEntity<List<OwnerDto>> getByName(@RequestParam(name = "name") String ownerName) {
        log.info("Find all owners with name = {}", ownerName);
        List<OwnerDto> ownerList = ownerService.findByFirstName(ownerName);
        return ResponseEntity.ok(ownerList);
    }

    @ApiOperation("Find owner by id")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/owners/{id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable Long id) {
        log.info("Find owner by id = {}", id);
        OwnerDto ownerDto = ownerService.findById(id);
        return ResponseEntity.ok(ownerDto);
    }

    }


