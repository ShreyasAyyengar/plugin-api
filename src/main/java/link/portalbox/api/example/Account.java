package link.portalbox.api.example;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Document(collection = "accounts")
public final class Account {
    /*
    TODO:
    Last login details
    - Time
    - Location
    - Device
    - Last IP
     */

    @Id
    private final UUID uid;
    private String username;
    private String email;
    private String password;
    private String accessToken;
    private boolean enabled;

    private String avatarUrl;
    private int defaultAvatarId;
    private String bannerUrl;
    private String bannerColor;

    /* Personal Information */
    private String firstName;
    private String lastName;
    private String displayName;
    private String bio;
    private String jobTitle;
    private String organisation;
    private String location;
    private String timezone;

    /* Contact Information */
    private String contact;
    private String twitter;
    private String gitHub;
    private String discord;

    /* Teams */
    private List<UUID> teams = new ArrayList<>();

    /* Roles */
    private List<Role> roles = new ArrayList<>();

    public Account(UUID uid, String username, String email, String password) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UUID getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Account resetAccessToken() {
        accessToken = AccountService.generateAccessToken();
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Account enable() {
        enabled = true;
        defaultAvatarId = ThreadLocalRandom.current().nextInt(6);
        return this;
    }

//    public String getAvatarUrl() {
//        if (avatarUrl == null || avatarUrl.isEmpty()) setAvatarUrl("https://cdn.mclicense.org/accounts/default/" + defaultAvatarId + ".png");
//        return avatarUrl;
//    }

//    public void setAvatarUrl(String avatarUrl) {
//        this.avatarUrl = avatarUrl;
//        setBannerColor(Util.averageHEXFromImageUrl(avatarUrl));
//    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getBannerColor() {
        return bannerColor;
    }

    public void setBannerColor(String bannerColor) {
        this.bannerColor = bannerColor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

//    public void addMarketplace(Marketplace marketplace, String url) {
//        marketplaceUrlMap.put(marketplace, url);
//    }
//
//    public void removeMarketplace(Marketplace marketplace) {
//        marketplaceUrlMap.remove(marketplace);
//    }
//
//    public Map<Marketplace, String> getMarketplaceUrlMap() {
//        return Collections.unmodifiableMap(marketplaceUrlMap);
//    }
//
//    public void setMarketplaceUrlMap(Map<Marketplace, String> marketplaceUrlMap) {
//        this.marketplaceUrlMap = marketplaceUrlMap;
//    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    public String getDiscord() {
        return discord;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public void addTeam(UUID uid) {
        teams.add(uid);
    }

    public void removeTeam(UUID uid) {
        teams.remove(uid);
    }

    public List<UUID> getTeams() {
        return Collections.unmodifiableList(teams);
    }

    public void setTeams(List<UUID> teams) {
        this.teams = teams;
    }

    public enum Role {
        MANAGEMENT, ENGINEER, SUPPORT, PARTNER
    }

    public void addRoles(Role... roles) {
        this.roles.addAll(List.of(roles));
    }

    public void removeRoles(Role... roles) {
        this.roles.removeAll(List.of(roles));
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public SecureAccount secure() {
        return new SecureAccount(uid, username, email, enabled, null, getBannerUrl(), bannerColor, firstName, lastName, displayName, bio, jobTitle, organisation, location, timezone, null, contact, twitter, gitHub, discord, teams, roles);
    }
}
