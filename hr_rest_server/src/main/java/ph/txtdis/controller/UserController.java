package ph.txtdis.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.NoArgsConstructor;
import ph.txtdis.domain.User;
import ph.txtdis.repository.UserRepository;

@RestController
@NoArgsConstructor
public class UserController {

    @Autowired
    UserRepository repository;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User e) {
        User user = repository.save(e);
        URI uri = buildURI(user);
        return new ResponseEntity<User>(user, httpHeaders(uri),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> listUsers() {
        List<User> users = repository.findByEnabledTrueOrderByUsernameAsc();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findUser(@PathVariable String id) {
        User user = repository.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/find", method = RequestMethod.GET)
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        User user = repository.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private URI buildURI(User user) {
        return user == null ? null
                : ServletUriComponentsBuilder.fromCurrentContextPath().path(
                        "/users/{id}").buildAndExpand(user.getId()).toUri();
    }

    private MultiValueMap<String, String> httpHeaders(URI uri) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return httpHeaders;
    }
}