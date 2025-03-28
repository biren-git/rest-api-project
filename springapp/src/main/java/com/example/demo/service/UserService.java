package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.YogaClass;
import com.example.demo.entity.InstructionalVideo;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.YogaClassRepository;
import com.example.demo.repository.InstructionalVideoRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private YogaClassRepository yogaClassRepository;

    @Autowired
    private InstructionalVideoRepository instructionalVideoRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @NonNull
    public Page<User> getAllUsers(@NonNull Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsersWithoutPagination() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with ID " + id + " not found");  // ✅ Proper 404 Handling
        }
        userRepository.deleteById(id);
    }

    public List<User> findUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public List<User> findUsersByEmailDomain(String domain) {
        return userRepository.findByEmailDomain(domain);
    }

    /*** ✅ New Methods for Enrolling Users and Assigning Videos ***/

    // Enroll a user in a yoga class
    public void enrollUserInClass(Long userId, Long classId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        YogaClass yogaClass = yogaClassRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Yoga class not found"));

        user.getEnrolledClasses().add(yogaClass);
        userRepository.save(user);
    }

    // Assign an instructional video to a user
    public void addVideoToUser(Long userId, Long videoId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        InstructionalVideo video = instructionalVideoRepository.findById(videoId)
                .orElseThrow(() -> new EntityNotFoundException("Video not found"));

        user.getInstructionalVideos().add(video);
        userRepository.save(user);
    }
}
