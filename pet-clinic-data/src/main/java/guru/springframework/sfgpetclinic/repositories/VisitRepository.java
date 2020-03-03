package guru.springframework.sfgpetclinic.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit , Long>{
    Visit findByDate(Date date);
}