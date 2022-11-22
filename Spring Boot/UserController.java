
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
  
  @GetMapping("/find/all")
	public List<User> getAll() {
		return userService.findAll();	
	}
  
}
