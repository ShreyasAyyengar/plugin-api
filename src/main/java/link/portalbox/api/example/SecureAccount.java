package link.portalbox.api.example;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record SecureAccount(UUID uid, String username, String email, boolean enabled, String avatarUrl, String bannerUrl, String bannerColor, String firstName, String lastName, String displayName, String bio, String jobTitle, String organisation, String location, String timezone, Map<String, String> marketplaceUrlMap, String contact, String twitter, String gitHub, String discord, List<UUID> teams, List<Account.Role> roles) {
}
