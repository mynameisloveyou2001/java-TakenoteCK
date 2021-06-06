package NET.taovan.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import NET.taovan.Model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	  @Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);
}
