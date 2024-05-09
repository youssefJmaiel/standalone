package tn.group.standalone.ms.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.group.standalone.ms.entity.Visit;
import tn.group.standalone.ms.repository.VisitRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public void delete(Long id) {
        Optional<Visit> optionalVisit = visitRepository.findById(id);
        optionalVisit.ifPresent(visitRepository::delete);
    }
}
