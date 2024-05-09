package tn.group.standalone.ms.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.group.standalone.ms.converter.VetConverter;
import tn.group.standalone.ms.converter.VetRequestConverter;
import tn.group.standalone.ms.dto.VetDto;
import tn.group.standalone.ms.dto.VetRequest;
import tn.group.standalone.ms.entity.Specialty;
import tn.group.standalone.ms.entity.Vet;
import tn.group.standalone.ms.repository.VetRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    public List<VetDto> getAll() {
        List<Vet> allVet = vetRepository.findAll();
        return allVet.stream().map(vet -> VetConverter.newInstance().convert(vet)).collect(Collectors.toList());
    }

    public VetDto create(VetRequest vetRequest) {
        Vet vet = VetRequestConverter.newInstance().convert(vetRequest);
        Vet savedVet = vetRepository.save(vet);
        return VetConverter.newInstance().convert(savedVet);
    }
    public List<Specialty> findSpecialiteByVet(String firstName, String lastName, String description){
        List<Vet> vets = vetRepository.findAll();
         List<Specialty> specialties = vets.stream()
                .filter(vet -> firstName.equals(vet.getFirstName()) && lastName.equals(vet.getLastName()))
                .map(Vet::getSpecialties)
                .flatMap(Collection::stream)
                .filter(specialty -> specialty.getDescription().contains(description))
                .collect(Collectors.toList());
         return specialties;
    }

}
