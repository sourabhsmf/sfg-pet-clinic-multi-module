package guru.springframework.sfgpetclinic.services;


import java.util.List;

import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner , Long>{
    List<Owner> findAllByLastNameLike(String lastName);
}