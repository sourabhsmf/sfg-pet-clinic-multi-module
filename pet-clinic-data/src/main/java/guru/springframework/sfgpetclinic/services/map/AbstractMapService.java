package guru.springframework.sfgpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.BaseEntity;
public abstract class AbstractMapService<T extends BaseEntity , Id extends Long>{
    protected Map<Long , T> map = new HashMap<>();

    public Set<T> findAll(){
        return new HashSet<>(map.values()); 
    }
    public T findById(Id id){
        return map.get(id);
    }
    public T save(T object){
        if(object != null && object.getId() != null){
            object.setId(getNextId());
            map.put(object.getId(),object);
        }else{
            throw new RuntimeException("Objects cannot be null");
        }
        return object;
    }
    public void deleteById(Id id){
        map.remove(id);
    }
    public void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
    public Long getNextId(){
        return map.size() > 0 ? Collections.max(map.keySet()) + 1 : 1 ;
    }

}