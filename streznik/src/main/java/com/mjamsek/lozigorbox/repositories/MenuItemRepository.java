package com.mjamsek.lozigorbox.repositories;

import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

	public List<MenuItem> findAllByParent(long id);
	
	/*@Query(value = "select i.id, i.ime, i.parent, i.tip, i.file_id from menu_item i where i.parent = :parent " +
			"and  ima_pravice(:upb, i.id, :dovoljenje)", nativeQuery = true)
	public List<MenuItem> poisciVseIzMapeZPravico(@Param("parent") long parent, @Param("upb") long upb, @Param("dovoljenje") int tip);
	*/
	@Query(value = "select i.id, i.ime, i.parent, i.tip, i.file_id from menu_item i where i.parent = 1", nativeQuery = true)
	public List<MenuItem> poisciVseIzMapeZPravico();
	
	public MenuItem findByIme(String ime);
	
	@Query(value="select i.id, i.ime, i.parent, i.tip, i.file_id " +
			"from menu_item i left join datoteka d on i.file_id= d.id " +
			"where ( (i.ime like concat('%', :query, '%') and i.file_id is null and i.id != 1) " +
			"or (d.ime like concat('%', :query, '%') and i.file_id is not null) ) limit 20", nativeQuery = true)
	public List<MenuItem> poisciZQueryjem(@Param("query") String query);
	
}
