package mykyta.anyshchenko.diploma.roomservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mykyta.anyshchenko.diploma.model.RoomClass;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RoomClass")
public class RoomClassDto implements RoomClass, Persistable<Integer> {
    @Id
    private Integer id;
    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }
}
