package link.portalbox.api.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = {"http://127.0.0.1:5173", "https://mclicense.org"}, maxAge = 3600)
@RestController
@RequestMapping(value = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
public final class AccountController {
    @Autowired
    private AccountService service;

    /*
     * TODO:
     * Get
     * Update
     * Delete
     */

    @GetMapping
    public ResponseEntity<List<Account>> allAccounts() {
        return ResponseEntity.accepted().body(service.findAll());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<SecureAccount> create(@RequestBody AccountService.CreateBody createBody) {
        return ResponseEntity.accepted().body(service.create(createBody));
    }

    @GetMapping(value = "/account")
    public ResponseEntity<Object> account(@RequestParam(value = "uid", required = false) UUID uid,
                                                   @RequestParam(value = "username", required = false) String string) {
        if (uid == null && string == null) return ResponseEntity.badRequest().build();
        if (uid == null) return service.account(string)
                .map(Account::secure)
                .map(Object.class::cast)
                .map(account -> ResponseEntity.accepted().body(account))
                .orElseGet(() -> ResponseEntity.noContent().build());
        return service.account(uid)
                .map(Account::secure)
                .map(Object.class::cast)
                .map(account -> ResponseEntity.accepted().body(account))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping(value = "/account")
    public ResponseEntity<Object> account(@RequestParam(value = "uid") UUID uid, @RequestBody AccountService.AccountBody account) {
        return service.account(uid, account) ?
                ResponseEntity.accepted().body("{\"grant\": true}") :
                ResponseEntity.badRequest().body("{\"grant\": false}");
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<String> auth(@RequestBody AccountService.AuthBody authBody) {
        Object[] auth = null;
        return auth.length == 4 ?
                ResponseEntity.badRequest().body("{ " +
                        "\"result\": " + auth[0] + ", " +
                        "\"uid\": \"" + auth[1] + "\", " +
                        "\"accessToken\": \"" + auth[2] + "\", " +
                        "\"error\": \"" + auth[3] + "\"" +
                        " }") :
                ResponseEntity.accepted().body("{ " +
                        "\"result\": " + auth[0] + ", " +
                        "\"uid\": \"" + auth[1] + "\", " +
                        "\"accessToken\": \"" + auth[2] + "\"" +
                        " }");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Optional<SecureAccount>> update(@RequestBody AccountService.UpdateBody updateBody) {
        return ResponseEntity.accepted().body(service.update(updateBody));
    }
}
