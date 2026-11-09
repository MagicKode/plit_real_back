package com.plit.real.service;

import com.plit.real.dto.MediaResponse;
import com.plit.real.dto.UploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MediaService {
    UploadResponse uploadFile(MultipartFile file) throws IOException;
    List<MediaResponse> getAllFiles();
}
