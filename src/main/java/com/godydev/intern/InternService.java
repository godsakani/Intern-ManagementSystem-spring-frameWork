package com.godydev.intern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternService {
    @Autowired private InternRepository repo;

    public List<Intern> listAll() {
        return (List<Intern>) repo.findAll();
    }

    public void save(Intern intern) {
        repo.save(intern);
    }

    public Intern get(Integer id) throws InternNotFoundException {
        Optional<Intern> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new InternNotFoundException("Could not fine any intern with ID " + id);
    }

    public void delete(Integer id) throws InternNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new InternNotFoundException("Could not fine any intern with ID " + id);
        }
        repo.deleteById(id);
    }
}
