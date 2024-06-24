package com.webserver.siatwiki.profile.entity;

import com.webserver.siatwiki.person.entity.Person;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long attachmentFileNo;

    @Column(name = "file_path", length = 2000)
    private String filePath;

    @Column(name = "attachment_file_name", length = 2000)
    private String attachmentFileName;

    @Column(name = "attachment_original_file_name", length = 2000)
    private String attachmentOriginalFileName;

    @Column(name = "attachment_file_size")
    private Long attachmentFileSize;

    @OneToOne(mappedBy = "profile")
    private Person person;

    @Builder
    public Profile(Long attachmentFileNo, String filePath, String attachmentFileName,
                   String attachmentOriginalFileName, Long attachmentFileSize) {
        this.attachmentFileNo = attachmentFileNo;
        this.filePath = filePath;
        this.attachmentFileName = attachmentFileName;
        this.attachmentOriginalFileName = attachmentOriginalFileName;
        this.attachmentFileSize = attachmentFileSize;
    }

    public void fetch(MultipartFile file, String filePath, String fileName) {
        this.attachmentFileSize = file.getSize();
        this.attachmentOriginalFileName = file.getOriginalFilename();
        this.filePath = filePath;
        this.attachmentFileName = fileName;
    }
}
