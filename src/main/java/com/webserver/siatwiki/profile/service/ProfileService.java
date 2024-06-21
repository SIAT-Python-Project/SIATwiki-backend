package com.webserver.siatwiki.profile.service;

import com.webserver.siatwiki.profile.entity.Profile;
import com.webserver.siatwiki.profile.repository.ProfileQueryDslRepository;
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
    private final ProfileQueryDslRepository profileQueryDslRepository;

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

    public String getProfileUrlByPersonId(Integer personId) {
        return profileQueryDslRepository.findByPersonId(personId)
                .getAttachmentFileName();
    }

    public String updateProfile(Integer profileId, MultipartFile file) throws IOException {
        Profile profile = profileQueryDslRepository.findByPersonId(profileId);

        if (file != null) {

            deleteLocalFile(profile.getFilePath());

            String fileName = UUID.randomUUID().toString() + "_" +file.getOriginalFilename();
            String filePath = savePath + "\\" + fileName;

            profile.fetch(file, filePath, fileName);

            file.transferTo(new File(filePath));
            profileRepository.save(profile);
        }

        return profile.getAttachmentFileName();
    }


    private void deleteLocalFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }
}
