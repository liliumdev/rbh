package models;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseModel<M> {

    private Long id;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract M duplicate(M model);
    public abstract void update(M data);
}