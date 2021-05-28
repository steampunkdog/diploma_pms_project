package mykyta.anyshchenko.diploma.roomservice.repository.postgresql;

import mykyta.anyshchenko.diploma.roomservice.model.RoomTagDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoomTagRepository extends JpaRepository<RoomTagDto, Integer> {

    @Query(value = "SELECT t.* FROM roomtag t inner join roomtoroomtag r on t.id = r.room_tag_id where r.room_id = ?1", nativeQuery = true)
    Collection<RoomTagDto> getTagsByRoom(Integer roomId);
}
