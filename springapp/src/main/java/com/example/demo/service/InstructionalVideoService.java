package com.example.demo.service;

import com.example.demo.entity.InstructionalVideo;
import com.example.demo.repository.InstructionalVideoRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import java.util.List;

@Service
public class InstructionalVideoService {

    @Autowired
    private InstructionalVideoRepository videoRepository;

    public InstructionalVideo createVideo(InstructionalVideo video) {
        return videoRepository.save(video);
    }

    public List<InstructionalVideo> getAllVideosWithoutPagination() {
        return videoRepository.findAll(); 
        }
        
    @NonNull
    public Page<InstructionalVideo> getAllVideos(@NonNull Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    public InstructionalVideo getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    public InstructionalVideo updateVideo(Long id, InstructionalVideo newVideo) {
        return videoRepository.findById(id).map(video -> {
            video.setTitle(newVideo.getTitle());
            video.setDescription(newVideo.getDescription());
            video.setUrl(newVideo.getUrl());
            return videoRepository.save(video);
        }).orElse(null);
    }

    public void deleteVideo(Long id) {
       if (!videoRepository.existsById(id)) {
         throw new EntityNotFoundException("InstructionalVideo with ID " + id + " not found");  // ✅ Proper 404 Handling
         }
       videoRepository.deleteById(id);
    }

    

    public List<InstructionalVideo> findByTitle(String title) {
        return videoRepository.findByTitle(title);
    }

    public List<InstructionalVideo> findByDescription(String description) {
        return videoRepository.findByDescription(description);
    }
}
