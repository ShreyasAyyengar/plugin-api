package link.portalbox.api.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public final class AccountService {
    @Autowired
    private AccountRepository repository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public List<Account> findAll() {
        return repository.findAll();
    }

    public static class CreateBody {
        private String email;
        private String username;
        private String password;

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public SecureAccount create(CreateBody create) {
        return repository.save(new Account(UUID.randomUUID(), create.username, create.email,
                null)).secure();
    }

    public static class AuthBody {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
//
//    public Object[] auth(AuthBody auth) {
//        return repository.findByUsername(auth.username)
////                .map(account -> passwordEncoder.matches(auth.password, account.getPassword()) ?
////                        new Object[]{true, account.getUid(), account.getAccessToken()} :
////                        new Object[]{false, null, null, "INVALID_PASSWORD"})
//                .orElseGet(() -> new Object[]{false, null, null, "INVALID_USERNAME"});
//    }

    public static class AccountBody {
        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }
    }

    public Optional<Account> account(UUID uid) {
        return repository.findById(uid);
    }

    public Optional<Account> account(String emailOrUsername) {
        return emailOrUsername.contains("@") ? // TODO: Replace with email regex
                repository.findByEmail(emailOrUsername) :
                repository.findByUsername(emailOrUsername);
    }

    public boolean account(UUID uid, AccountBody body) {
        return repository.findById(uid)
                .map(account -> account.getAccessToken().equals(body.accessToken))
                .orElse(false);
    }

    public Optional<Account> enable(UUID uid) {
        Optional<Account> optional = account(uid);
        optional.ifPresent(account -> account.setEnabled(true));
        return optional;
    }

    public Optional<Account> enable(String emailOrUsername) {
        Optional<Account> optional = account(emailOrUsername);
        optional.ifPresent(account -> account.setEnabled(true));
        return optional;
    }

    public Optional<Account> disable(UUID uid) {
        Optional<Account> optional = account(uid);
        optional.ifPresent(account -> account.setEnabled(false));
        return optional;
    }

    public Optional<Account> disable(String emailOrUsername) {
        Optional<Account> optional = account(emailOrUsername);
        optional.ifPresent(account -> account.setEnabled(false));
        return optional;
    }

    public void terminate(UUID uid) {
        repository.deleteById(uid);
    }

    public void terminate(String emailOrUsername) {
        if (emailOrUsername.contains("@")/* TODO: Replace with email regex */) {
            repository.deleteByEmail(emailOrUsername);
            return;
        }
        repository.deleteByUsername(emailOrUsername);
    }

    public static class UpdateBody {
        private UUID uid;
        private Map<String, Object> changes;

        public UUID getUid() {
            return uid;
        }

        public Map<String, Object> getChanges() {
            return changes;
        }
    }

    public Optional<SecureAccount> update(UpdateBody update) {
        return account(update.uid).map(account -> {
            Method[] methods = account.getClass().getDeclaredMethods();
            for (Map.Entry<String, Object> change : update.changes.entrySet()) {
                String changeKey = change.getKey().toLowerCase();

                if (changeKey.equals("password")) {
          //          account.setPassword(passwordEncoder.encode(String.valueOf(change.getValue())));
                    account.resetAccessToken();
                    continue;
                }

                for (Method method : methods)
                    if (method.getName().contains("set") && method.getName().toLowerCase().contains(changeKey)) {
                        try {
                            method.invoke(account, change.getValue());
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
            }
            return repository.save(account).secure();
        });
    }

    public boolean exists(UUID uid) {
        return repository.existsById(uid);
    }

    public boolean exists(String emailOrUsername) {
        return emailOrUsername.contains("@") ? // TODO: Replace with email regex
                repository.existsByEmail(emailOrUsername) :
                repository.existsByUsername(emailOrUsername);
    }

    public boolean disabled(UUID uid) {
        return account(uid).map(Account::isEnabled).orElse(false);
    }

    public boolean disabled(String emailOrUsername) {
        return account(emailOrUsername).map(Account::isEnabled).orElse(false);
    }

    private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateAccessToken() {
        StringBuilder stringBuilder = new StringBuilder(128);
        for (int i = 0; i < 128; i++) stringBuilder.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        return stringBuilder.toString();
    }
}
