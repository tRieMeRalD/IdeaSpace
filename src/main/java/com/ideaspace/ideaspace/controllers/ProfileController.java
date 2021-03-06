package com.ideaspace.ideaspace.controllers;

import java.util.Optional;

import com.ideaspace.ideaspace.models.Profile;
import com.ideaspace.ideaspace.repositories.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    @Autowired
    ProfileRepository profileRepository;

    /*
     * Route: /profile Status: PUBLIC FUNC: Get listings of profiles
     */
    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public Iterable<Profile> profile() {
        return profileRepository.findAll();
    }

    /*
     * Route: /profile/:id Status: PUBLIC FUNC: Save profile info to database
     */
    @RequestMapping(method = RequestMethod.POST, value = "/profile/{id}")
    public Profile save(@RequestBody Profile profile) {
        profileRepository.save(profile);

        return profile;
    }

    /*
     * Route: /profile/:id Status: PUBLIC FUNC: Display profile by id (for sharing
     * links)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/profile/{id}")
    public Optional<Profile> show(@PathVariable String id) {
        return profileRepository.findById(id);
    }

    /*
     * Route: /profile/:id Status: PUBLIC FUNC: Delete account
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/profile/{id}")
    public String delete(@PathVariable String id) {
        Optional<Profile> optProfile = profileRepository.findById(id);
        Profile profile = optProfile.get();
        profileRepository.delete(profile);

        return "";
    }

    /*
     * Route: /profile/:id Status: PUBLIC FUNC: Update profile settings
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/profile/{id}")
    public Profile update(@PathVariable String id, @RequestBody Profile profile) {
        Optional<Profile> optProfile = profileRepository.findById(id);
        Profile p = optProfile.get();

        if (profile.getProfilePic() != null)
            p.setProfilePic(profile.getProfilePic());
        if (profile.getBgPic() != null)
            p.setBgPic(profile.getBgPic());
        if (profile.getBioInfo() != null)
            p.setBioInfo(profile.getBioInfo());
        if (profile.getInstagram() != null)
            p.setInstagram(profile.getInstagram());
        if (profile.getFacebook() != null)
            p.setFacebook(profile.getFacebook());
        if (profile.getLinkedin() != null)
            p.setLinkedin(profile.getLinkedin());
        if (profile.getGithub() != null)
            p.setGithub(profile.getGithub());

        profileRepository.save(p);

        return p;
    }
}