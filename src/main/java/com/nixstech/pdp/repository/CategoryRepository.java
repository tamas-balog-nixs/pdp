package com.nixstech.pdp.repository;

import com.nixstech.pdp.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

  @Query("select category from Category category where category.enabled = true and category.alias = ?1")
  Category findByAliasEnabled(String alias);

  Category findByAlias(String alias);

  Long countById(Integer id);

  @Query("SELECT c FROM Category c WHERE c.title = :title")
  Category findByTitle(@Param("title") String title);

  @Query("SELECT c FROM Category c WHERE c.parent.id is NULL")
  Page<Category> findRootCategories(Pageable pageable);
}
