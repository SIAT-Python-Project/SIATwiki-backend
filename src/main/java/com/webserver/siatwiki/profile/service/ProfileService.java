package com.webserver.siatwiki.profile.service;

import com.webserver.siatwiki.common.response.error.CustomException;
import com.webserver.siatwiki.profile.entity.Profile;
import com.webserver.siatwiki.profile.repository.ProfileQueryDslRepository;
import com.webserver.siatwiki.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.webserver.siatwiki.common.response.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ProfileService {
    @Value("${file.save.path}")
    private String savePath;
    private final ProfileRepository profileRepository;
    private final ProfileQueryDslRepository profileQueryDslRepository;

    @Transactional
    public Long saveProfile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_" +file.getOriginalFilename();
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
        } catch (IOException e) {
            throw new CustomException(PROFILE_SAVE_FAIL);
        }
    }


    @Transactional(readOnly = true)
    public String getProfileUrlByPersonId(Integer personId) {
        Profile profile = profileQueryDslRepository.findByPersonId(personId);

        if (profile == null) {
            throw new CustomException(PROFILE_NOT_FOUND);
        }

        return profile.getAttachmentFileName();
    }

    @Transactional
    public String updateProfile(Integer personId, MultipartFile file) {
        Profile profile = profileQueryDslRepository.findByPersonId(personId);

        if (profile == null) {
            throw new CustomException(PROFILE_NOT_FOUND);
        }

        try {
            if (file != null) {
                deleteLocalFile(profile.getFilePath());

                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                String filePath = savePath + "\\" + fileName;

                profile.fetch(file, filePath, fileName);

                file.transferTo(new File(filePath));
                profileRepository.save(profile);
            }
        } catch (IOException e) {
            throw new CustomException(PROFILE_SAVE_FAIL);
        }

        return profile.getAttachmentFileName();
    }


    private void deleteLocalFile(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new CustomException(PROFILE_DELETE_FAIL);
        }

        file.delete();
    }
}
