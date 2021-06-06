package NET.taovan.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NET.taovan.Model.Notes;
import NET.taovan.Repository.NoteRepository;

@Service
public class NoteService {
	@Autowired
	private NoteRepository note4;

	public List<Notes> listAll(String keyword) {
		if (keyword != null) {
			return note4.findAll(keyword.toLowerCase());
		}
		return note4.findAll();
	}
}
