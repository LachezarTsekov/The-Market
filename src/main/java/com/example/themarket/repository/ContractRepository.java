package com.example.themarket.repository;

import com.example.themarket.model.entity.Contract;
import com.example.themarket.model.projection.ActiveContractProjection;
import com.example.themarket.model.projection.ClosedContractProjection;
import com.example.themarket.model.projection.ContractBySellerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query(value = "select * from contracts " +
            "where item_id = :itemId", nativeQuery = true)
    Contract getByItemId(@Param("itemId") Long itemId);

    @Query(value = "select users.id as sellerId, users.username as sellerUsername," +
            " items.id as itemId , contracts.price, contracts.active from contracts " +
            "join users  " +
            "on contracts.seller_id = users.id " +
            "join items " +
            "on contracts.item_id = items.id " +
            "where contracts.active = true", nativeQuery = true)
    List<ActiveContractProjection> getAllActive();

    @Modifying
    @Transactional
    @Query(value = " update contracts " +
            "set price = :price " +
            "where contracts.item_id = :itemId " +
            "and contracts.active = true ", nativeQuery = true)
    void updatePrice(@Param("itemId") Long itemId,@Param("price") BigDecimal price);

    @Query(value = "update contracts" +
            " set contracts.active = false " +
            " and contracts.buyer_id = :buyerId " +
            " where contracts.item_id = :itemId " +
            "  ", nativeQuery = true)
    void closeContract(@Param("itemId") Long itemId,@Param("buyerId") Long buyerId);

//    @Query(value = "select (select users.id  from users " +
//            "where users.id = contracts.seller_id) as sellerId, " +
//            "(select users.username from users " +
//            "where users.id = contracts.seller_id)  as sellerUsername, " +
//            "(select users.id  from users " +
//            "where users.id = contracts.buyer_id) as buyerId,  " +
//            "(select users.username from users " +
//            "where users.id = contracts.buyer_id)  as buyerUsername, " +
//            "(select items.id  from items " +
//            "where items.id = contracts.item_id) as itemId, " +
//            "contracts.active , contracts.price from contracts " +
//            "where contracts.active = false " +
//            "and :buyerId is null or contracts.buyer_id = :buyerId " +
//            "or :sellerId is null or contracts.seller_id = :sellerId  " +
//            "or :itemId is null or contracts.item_id = :itemId", nativeQuery = true)
   @Query(value = "select seller.id as sellerId, seller.username as sellerUsername, " +
           " buyer.id as buyerId, buyer.username as buyerUsername, " +
           " items.id as itemId , contracts.price, contracts.active from contracts " +
           "join users as buyer " +
           "on contracts.buyer_id = buyer.id " +
           "join users as seller " +
           "on contracts.seller_id = seller.id " +
           "join items " +
           "on contracts.item_id = items.id " +
           "where contracts.active = false " +
           "and (:sellerId is null or contracts.seller_id = :sellerId) " +
           "and (:buyerId is null or contracts.buyer_id = :buyerId) " +
           "and (:itemId is null or contracts.item_id = :itemId)"
           , nativeQuery = true)
    List<ClosedContractProjection> getAllClosed(@Param("itemId") Long itemId,
                                                @Param("sellerId") Long sellerId,
                                                @Param("buyerId") Long buyerId);

    @Query(value = "select contracts.id as contractId, contracts.seller_id as sellerId, " +
            "seller.username as sellerUsername, " +
            "buyer.id as buyerId, buyer.username as buyerUsername, " +
            "contracts.price, items.id as itemId, contracts.active  from contracts " +
            "join  users as seller on contracts.seller_id = seller.id " +
            "left join users as buyer on contracts.buyer_id = buyer.id " +
            "join items  on contracts.item_id = items.id " +
            "where contracts.seller_id = :sellerId ", nativeQuery = true)
    List<ContractBySellerProjection> getAllBySellerId(@Param("sellerId") Long sellerId);
}
