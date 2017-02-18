package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Lilium on 17.1.2017.
 */

// TODO Remove DiningTableType model and successfully compile the project
/* This is an unused model, it won't be used in the future. For some
* very weird reason, the project won't run properly without the class,
* it won't even compile. */
@Entity
public class DiningTableType extends BaseModel<DiningTableType>  {
    private String name;
    private Integer persons;
    //private List<DiningTable> diningTables;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "persons")
    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

/*
    @OneToMany(mappedBy = "diningTableType")
    public List<DiningTable> getDiningTables() {
        return diningTables;
    }

    public void setDiningTables(List<DiningTable> diningTables) {
        this.diningTables = diningTables;
    }*/

    @Override
    public DiningTableType duplicate(DiningTableType model)
    {
        DiningTableType d = new DiningTableType();
        d.setName(model.getName());
        d.setPersons(model.getPersons());

        return d;
    }

    @Override
    public void update(DiningTableType data)
    {
        setName(data.getName());
        setPersons(data.getPersons());
    }
}
