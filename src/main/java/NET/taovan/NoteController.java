package NET.taovan;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {
	@Autowired
	private UserRepository repo;
	@Autowired
	private NoteRepository note1;
	@Autowired
	private NoteService note2;

	@GetMapping("/addnote")
	public String showNotes(Model model) {
		model.addAttribute("note", new Notes());
		return "addNote";

	}

	@PostMapping("/save_note")
	public String takeNoteSuccess(Notes note, Model model, Principal principal) {
		User user = repo.findByEmail(principal.getName());
		note.setUser(user);
		note1.save(note);
		return "notesuccess";
	}

	@RequestMapping("/takenotes")
	public String viewNotes(Model model, @Param("keyword1") String keyword) {
		List<Notes> notes = note2.listAll(keyword);
		model.addAttribute("takenote1", notes);
		return "shownote";
	}

	@RequestMapping(value = "/takenotes/filter")
	public String filterNotes(Model model, @RequestParam("user_id") Long userId) {
		User user = repo.findById(userId).get();
		List<Notes> notes = note1.findByUser(user);
		model.addAttribute("takenote1", notes);
		return "noteofUser";
	}

	@RequestMapping("/deleted/{id}")
	public String deleteNotes(@PathVariable("id") Long id, Model model) {
		note1.deleteById(id);
		List<Notes> listnote = note1.findAll();
		model.addAttribute("takenote1", listnote);
		return "shownote";
	}

	@RequestMapping("/edit/{id}")
	public String showEdit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("note", note1.findById(id).orElse(null));
		return "edit";
	}

	@RequestMapping("/notes/update")
	public String update(@Param("keyword1") String keyword, Model model, @RequestParam Long id,
			@RequestParam String title, @RequestParam String content, @RequestParam Date ngaytao) {
		Notes note = note1.findById(id).orElse(null);
		note.setTieude(title);
		note.setNoidung(content);
		note.setNgaytao(ngaytao);
		note1.save(note);
		List<Notes> notes = note2.listAll(keyword);
		model.addAttribute("takenote1", notes);
		return "shownote";
	}
}
