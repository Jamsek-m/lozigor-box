package com.mjamsek.lozigorbox.repositories;

import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

	public List<MenuItem> findAllByParent(long id);
	
	public MenuItem findByIme(String ime);
	
	@Query(value="select i.id, i.ime, i.num_of_childs, i.parent, i.tip, i.file_id " +
			"from menu_item i left join datoteka d on i.file_id= d.id " +
			"where ( (i.ime like concat('%', :query, '%') and i.file_id is null and i.id != 1) " +
			"or (d.ime like concat('%', :query, '%') and i.file_id is not null) ) limit 20", nativeQuery = true)
	public List<MenuItem> poisciZQueryjem(@Param("query") String query);
	
	/*default List<MenuItem> poisciZQueryjem(String query) {
		return poisciZQueryjemAll(query, new PageRequest(0, 20));
	}*/
	
}
