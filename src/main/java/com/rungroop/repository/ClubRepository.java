package com.rungroop.repository;

import com.rungroop.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByTitle(String title);
    // JPQL of JPA -> nativeQuery-> write mySQL , buộc có dấu :
    @Query(value = "select c.* from clubs c where c.title like concat('%', :query , '%') " ,nativeQuery = true)
    public List<Club> searchClubs( @Param("query") String query);
}
