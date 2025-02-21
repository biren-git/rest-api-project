package com.example.demo.controller;

import com.example.demo.entity.InstructionalVideo;
import com.example.demo.service.InstructionalVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class InstructionalVideoController {

    @Autowired
    private InstructionalVideoService videoService;

    @PostMapping
    public InstructionalVideo createVideo(@RequestBody InstructionalVideo video) {
        return videoService.createVideo(video);
    }

    @GetMapping("/{id}")
    public InstructionalVideo getVideoById(@PathVariable Long id) {
        return videoService.getVideoById(id);
    }

    @PutMapping("/{id}")
    public InstructionalVideo updateVideo(@PathVariable Long id, @RequestBody InstructionalVideo updatedVideo) {
        return videoService.updateVideo(id, updatedVideo);
    }

    @DeleteMapping("/{id}")
    public void deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
    }

    @GetMapping
    public List<InstructionalVideo> getAllVideos() {
        return videoService.getAllVideosWithoutPagination();
    }

    @GetMapping("/paginate")
    public Page<InstructionalVideo> getAllVideosWithPagination(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "title") String sortBy,
        @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Pageable pageable = PageRequest.of(page, size, 
            sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
        );
        return videoService.getAllVideos(pageable);
    }

    @GetMapping("/search/title")
    public List<InstructionalVideo> getByTitle(@RequestParam String title) {
        return videoService.findByTitle(title);
    }

    @GetMapping("/search/description")
    public List<InstructionalVideo> getByDescription(@RequestParam String description) {
        return videoService.findByDescription(description);
    }
}
