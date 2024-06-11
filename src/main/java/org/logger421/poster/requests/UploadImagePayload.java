package org.logger421.poster.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadImagePayload {
    MultipartFile imageFile;
}
