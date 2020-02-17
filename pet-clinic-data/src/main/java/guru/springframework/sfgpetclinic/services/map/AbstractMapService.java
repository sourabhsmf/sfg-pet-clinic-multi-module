package guru.springframework.sfgpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public abstract class AbstractMapService<T,Id>{
    protected Map<Id, T> map = new HashMap<>();

    public Set<T> findAll(){
        return new HashSet<>(map.values()); 
    }
    public T findById(Id id){
        return map.get(id);
    }
    public T save(Id id , T object){
        map.put(id, object);
        return object;
    }
    public void deleteById(Id id){
        map.remove(id);
    }
    public void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

}