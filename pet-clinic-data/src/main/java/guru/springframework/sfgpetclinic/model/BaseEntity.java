package guru.springframework.sfgpetclinic.model;


/**
 * 
 */
public class BaseEntity extends BaseEntity {
    public Long Id;
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