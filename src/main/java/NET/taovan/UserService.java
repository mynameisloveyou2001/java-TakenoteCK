package NET.taovan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	public List<User> listAll(){
		return repo.findAll();
	}
	
	
	public void save(User user) {
		repo.save(user);
	}
	
	public User get(Long id) {
		return repo.findById(id).get();
	}
	public User findUserByEmail(String email) {
	    return repo.findByEmail(email);
	}
	public void delete(Long id) {
		repo.deleteById(id);
	}
	Optional<User> findByIdd(Long id) {
		return repo.findById(id);
	}
}
