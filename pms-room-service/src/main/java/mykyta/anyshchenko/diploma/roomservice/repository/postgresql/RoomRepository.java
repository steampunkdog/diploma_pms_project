package mykyta.anyshchenko.diploma.roomservice.repository.postgresql;

import mykyta.anyshchenko.diploma.roomservice.model.RoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomDto, Integer> {
}
