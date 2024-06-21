package com.webserver.siatwiki.profile.service;

import com.webserver.siatwiki.profile.entity.Profile;
import com.webserver.siatwiki.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {
    @Value("${file.save.path}")
    private String savePath;
    private final ProfileRepository profileRepository;

    public Long saveProfile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" +file.getOriginalFilename();

        String filePath = savePath + "\\" + fileName;

        Profile profile = Profile.builder()
                .filePath(filePath)
                .attachmentFileName(fileName)
                .attachmentOriginalFileName(file.getOriginalFilename())
                .attachmentFileSize(file.getSize())
                .build();

        file.transferTo(new File(filePath));
        Profile newProfile = profileRepository.save(profile);

        return newProfile.getAttachmentFileNo();
    }

    public String getProfileById(Long profileId) throws NoSuchElementException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow();

        return profile.getAttachmentFileName();
    }
}
