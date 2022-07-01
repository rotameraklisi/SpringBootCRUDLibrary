package com.example.library.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value="SELECT i FROM Items i WHERE i.type=?1 and i.flag=?2")
    List<Item> findByTypeAndFlag(int type, Boolean flag);

    @Query(value="SELECT i FROM Items i WHERE i.type=?1")
    List<Item> findAllByType(int type);

    @Query(value="SELECT i FROM Items i WHERE i.type=?1 and id=?2")
    Item findByIdAndType(Long itemId, int type);

    @Query(value="SELECT i.flag FROM Items i WHERE i.type=?1 and name=?2")
    Item findByTypeAndName(int type, String name);
    
}
