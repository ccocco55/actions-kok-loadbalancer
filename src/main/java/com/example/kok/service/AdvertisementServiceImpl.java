package com.example.kok.service;

import com.example.kok.dto.AdvertisementBackgroundFileDTO;
import com.example.kok.dto.AdvertisementDTO;
import com.example.kok.repository.AdvertisementBackgroundFileDAO;
import com.example.kok.repository.AdvertisementDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementDAO advertisementDAO;
    private final AdvertisementBackgroundFileDAO advertisementBackgroundFileDAO;
    private final S3Service s3Service;

    @Override
    public List<AdvertisementDTO> getAllAdvertisements() {
        List<AdvertisementDTO> advertisements = advertisementDAO.findAll();

        advertisements.forEach(advertisement -> {
            List<AdvertisementBackgroundFileDTO> files = advertisementBackgroundFileDAO.advertisementBackgroundFile(advertisement.getId());

            if (!files.isEmpty()) {
                String preSignedUrl = s3Service.getPreSignedUrl(files.get(0).getFilePath(),Duration.ofMinutes(10));
                advertisement.setFilePath(preSignedUrl);
            }
        });

        return advertisements;
    }
}
