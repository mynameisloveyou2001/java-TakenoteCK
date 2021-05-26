package NET.taovan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NoteService{
	@Autowired
	private NoteRepository note4;
	
	public List<Notes>listAll(String keyword){
		if(keyword != null) {
			return note4.findAll(keyword);
		}
		return note4.findAll();
	}
	public Notes get(Long stt) {
		return note4.findById(stt).get();
	}
	
	public void save(Notes note) {
		note4.save(note);
	}
	public void delete(Long stt) {
		note4.deleteById(stt);
	}
	Optional<Notes> findByStt(Long stt) {
		return note4.findById(stt);
	}
}
