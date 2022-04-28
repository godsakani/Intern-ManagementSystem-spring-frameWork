package com.godydev.intern;

import com.godydev.intern.Intern;
import com.godydev.intern.InternRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest
public class InternRepositoryTests {

    private final InternRepository repo;

    @Autowired
    public InternRepositoryTests(InternRepository repo) {
        this.repo = repo;
    }

    @Test
    public void testAddNew() {
        Intern intern = new Intern();
        //intern.setId();
        intern.setEmail("Gills@gmail.com");
        intern.setPassword("24334we222");
        intern.setFirstName("Gills");
        intern.setLastName("lambive");

        Intern savedIntern = repo.save(intern);

        //Used Asssertion to test APIs
        Assertions.assertThat(savedIntern).isNotNull();
        Assertions.assertThat(savedIntern.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll(){
        Iterable<Intern> interns = repo.findAll();
        Assertions.assertThat(interns).hasSizeGreaterThan(0);

        for (Intern intern : interns) {
            System.out.println(intern);
        }
    }

    @Test
    public void testUpdate() {
        Integer internId = 1;
        Optional<Intern> optionalIntern = repo.findById(internId);
        Intern intern = optionalIntern.get();
        intern.setPassword("wel2000");
        repo.save(intern);

        Intern updatedIntern = repo.findById(internId).get();
        Assertions.assertThat(updatedIntern.getPassword()).isEqualTo("wel2000");
    }

    @Test
    public void testGet() {
        Integer internId = 2;
        Optional<Intern> optionalIntern = repo.findById(internId);

        Assertions.assertThat(optionalIntern).isPresent();
        System.out.println(optionalIntern.get());
    }

    @Test
    public  void testDelete() {
        Integer internId = 8;
        repo.deleteById(internId);

        Optional<Intern> optionalIntern = repo.findById(internId);
        Assertions.assertThat(optionalIntern).isNotPresent();
    }

    @Test
    void isEnabled() {
    }

    @Test
    void setEnabled() {
    }
}
