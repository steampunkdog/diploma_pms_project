package mykyta.anyshchenko.diploma.roomservice.repository.postgresql;

import mykyta.anyshchenko.diploma.roomservice.model.RoomClassDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomClassRepository extends JpaRepository<RoomClassDto, Integer> {
}
