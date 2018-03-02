package com.mjamsek.lozigorbox.repositories;

import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
	
	public MenuItem findByIme(String ime);
	
	public List<MenuItem> findAllByParent(long id);
	
	@Query(value = "select i from MenuItem i where i.parent = :parent " +
			"and function('ima_pravice', :upb, i.id, :tip) = true")
	public List<MenuItem> poisciVseIzMapeZPravico(
			@Param("parent") long parent,
			@Param("upb") long uporabnik,
			@Param("tip") int tipDovoljenja
	);
	
	@Query("SELECT i, d FROM MenuItem i LEFT JOIN i.file d WHERE " +
			"( (i.ime LIKE CONCAT('%', :query, '%') AND i.file IS NULL AND i.id <> 1) " +
			"OR (d.ime LIKE CONCAT('%', :query, '%') AND i.file IS NOT NULL) ) " +
			"AND FUNCTION('ima_pravice', :upb, i.id, :tip) = true")
	public List<MenuItem> posciZQueryjem(
			@Param("query") String query,
			@Param("upb") long uporabnik,
			@Param("tip") int tipDovoljenja,
			Pageable pageable
	);
	
}
