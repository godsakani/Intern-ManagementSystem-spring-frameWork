package com.godydev.intern;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternRepository extends CrudRepository<Intern, Integer> {
    public Long countById(Integer id);

}
