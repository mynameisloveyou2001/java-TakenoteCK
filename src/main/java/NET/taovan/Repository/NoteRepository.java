package NET.taovan.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import NET.taovan.Model.Notes;
import NET.taovan.Model.User;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {
	@Query("SELECT n FROM Notes n WHERE lower(n.tieude) lIKE %?1%")
	public List<Notes> findAll(String keyword);
	List<Notes> findByUser(User user);
}
