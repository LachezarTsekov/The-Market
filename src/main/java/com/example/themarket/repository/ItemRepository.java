package com.example.themarket.repository;

import com.example.themarket.model.entity.Item;
import com.example.themarket.model.projection.ItemViewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select  i.name, i.id, u.id as ownerId, u.username as ownerUsername" +
            " from items as i " +
            " join users as u " +
            " on i.owner_id = u.id " +
            " where u.id = :ownerId ", nativeQuery = true)
    List<ItemViewProjection> getAllByOwnerId(@Param("ownerId") Long id);
}
