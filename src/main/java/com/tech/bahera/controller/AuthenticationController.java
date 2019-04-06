package com.tech.bahera.controller;

import java.util.Optional;


//@RestController
//@RequestMapping("/token")
public class AuthenticationController {

    /*@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
    	
    	String tokenNok = "dummmy";
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final Optional<User> user = userService.findById(loginUser.getUsername());
        if (user.isPresent()) {
        	final String token = jwtTokenUtil.generateToken(user.get());
        	return ResponseEntity.ok(new AuthToken(token));
        }
        
        return ResponseEntity.ok(new AuthToken(tokenNok));
    }
*/
}
