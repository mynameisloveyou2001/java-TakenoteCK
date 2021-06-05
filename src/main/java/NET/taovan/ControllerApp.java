package NET.taovan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerApp {
	@Autowired
	private UserRepository repo;

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

	@RequestMapping("/editUser/{id}")
	public ModelAndView editUser(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", repo.findById(id).orElse(null));
		mav.setViewName("editUser");
		return mav;
	}

	@RequestMapping("/user/updates")
	public String update1(Model model, @RequestParam Long id, @RequestParam String email, @RequestParam String password,
			@RequestParam String firstName, @RequestParam String lastName) {
		User user = repo.findById(id).orElse(null);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}

	@GetMapping("/notehome")
	public String viewNote(@AuthenticationPrincipal CustomUserDetails note, Model model) {
		model.addAttribute("notee", note);
		return "noteUser";
	}
}