package tn.group.standalone.ms.api;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.group.standalone.ms.service.VisitService;

@Slf4j
@RestController
@RequestMapping("/v1")
@Validated
@AllArgsConstructor
@Timed
public class VisitController {

    private final VisitService visitService;

    @ApiOperation("Delete visit with id")
    @Timed
    @DeleteMapping(path = "/v1/visits/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Delete visit with id = {}", id);
        visitService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    public List<String> getByRole(String projectName){
//        Project project = projectRepository.findByName(projectName);
//        List<String> characters = project.getName().split("");
//        return project.getRoles().stream()
//                .filter(r -> filterRole(r, characters) )
//                .map(r -> r.getTeams()).flatMap(Collection::stream).map(c -> c.getName())
//                .collect(Collectors.toList());
//
//    }
//
//    private Boolean filterRole(Role role, List<String> characters){
//        return role.getName().split("").stream().anyMatch(c -> characters.contains(c));
//    }
}
