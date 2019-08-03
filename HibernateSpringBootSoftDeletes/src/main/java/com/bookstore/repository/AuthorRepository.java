package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * FROM author", nativeQuery = true)
    List<Author> findAllIncludingDeleted();

    @Query(value = "SELECT * FROM author AS a WHERE a.deleted = true", nativeQuery = true)
    List<Author> findAllOnlyDeleted();

    @Transactional
    @Query(value = "UPDATE author SET deleted = false WHERE id = ?1", nativeQuery = true)
    @Modifying
    public void restoreAuthorById(Long id);
}
