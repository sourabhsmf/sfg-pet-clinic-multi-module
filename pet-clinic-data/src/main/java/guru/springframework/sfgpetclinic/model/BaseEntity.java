package guru.springframework.sfgpetclinic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 */
@MappedSuperclass
public class BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    public BaseEntity(){
        this.Id = 0L;
    }
    public BaseEntity(Long Id){
        this.Id = Id;
    }
    public void setId(Long Id){
        this.Id = Id;
    }
    public Long getId(){
        return this.Id;
    }
}