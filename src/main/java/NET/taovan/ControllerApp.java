package NET.taovan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerApp {
	@Autowired
	private UserRepository repo;
	@Autowired
	private NoteRepository note;
	@Autowired
	private UserService repo3;
	@Autowired
	private NoteService note2;

	@GetMapping("")
	public String home() {
		return "homepage";
	}

	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}


	@PostMapping("/process_register")
	public String processRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
		return "register_success";
	}

	@GetMapping("/list_users")
	public String viewUsersList(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	
	@GetMapping("/takenote")
	public String showNotes(Model model) {
	
//		ModelAndView modelAndView = new ModelAndView();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User user = repo3.findUserByEmail(auth.getName());
//		modelAndView.addObject("currentUser", user);
//		modelAndView.addObject("fullName", "Welcome " + user.getFullname());
//		modelAndView.setViewName("editNote");
//		return modelAndView;
		model.addAttribute("note", new Notes());
		return "editNote";

	}
	
	
	@PostMapping("/take_notes")
	public String takeNoteSuccess(Notes note, Model model) {
		note2.save(note);
		return "notesuccess";	
	}
	
	@RequestMapping("/takenotes")
	public String viewNotes(Model model, @Param("keyword") String keyword) {
		List<Notes> notes = note2.listAll(keyword);
		model.addAttribute("takenote1", notes);
		return "takenote";
	}


	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") Long id, Model model) {
		repo3.delete(id);
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}

	@RequestMapping("/deleted/{stt}")
	public String deleteNotes(@PathVariable(name = "stt") Long stt, Model model) {
		note2.delete(stt);
		List<Notes> listnote = note.findAll();
		model.addAttribute("takenote1", listnote);
		return "takenote";
	}

	@RequestMapping("/edit/{stt}")
	public ModelAndView showEdit(@PathVariable(name = "stt") Long stt) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("note", note.findById(stt).orElse(null));
		mav.setViewName("edit");
		return mav;
	}
	@RequestMapping("/notes/update")
	public String update(@Param("keyword") String keyword,Model model,@RequestParam Long stt, @RequestParam String title, @RequestParam String content, @RequestParam String ngaytao) {
		Notes note = note2.findByStt(stt).orElse(null);
		note.setTieude(title);
		note.setNoidung(content);
		note.setNgaytao(ngaytao);
		note2.save(note);
		List<Notes> notes = note2.listAll(keyword);
		model.addAttribute("takenote1", notes);
		return "takenote";
	}
	@GetMapping("/notehome")
	public String viewNote(@AuthenticationPrincipal CustomUserDetails note,Model model) {
		model.addAttribute("notee",note);
		return "viewnote";
}
}