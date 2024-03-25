package com.rungroup.web.respositoy;

import com.rungroup.web.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

// we are leveraging Spring Data JPA to implement the DAO pattern. Spring Data JPA automatically generates the implementation of the DAO interface based on conventions and provides a wide range of database operations out of the box, reducing the need for boilerplate code.
//
//DAO pattern in Spring Boot enables you to abstract away the complexities of data access and focus on writing business logic in a clean and maintainable manner.
// Common database operation includes reading, writing, modifying data,... like get, findById
public interface ClubRepository extends JpaRepository<Club,Long> {
    Optional<Club> findByTitle(String url);
    @Query("SELECT c from Club c WHERE c.title LIKE CONCAT('%', :query, '%')")
    List<Club> searchClubs(String query);
}
