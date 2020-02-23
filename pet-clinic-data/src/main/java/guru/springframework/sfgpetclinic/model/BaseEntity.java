package guru.springframework.sfgpetclinic.model;


/**
 * 
 */
public class BaseEntity{
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