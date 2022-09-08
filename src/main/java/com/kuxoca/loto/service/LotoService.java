package com.kuxoca.loto.service;

import com.kuxoca.loto.entity.Loto;
import com.kuxoca.loto.entity.LotoDto;
import com.kuxoca.loto.repository.LotoRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Log4j2
public class LotoService {
    private final FileStorageService fileStorageService;
    private final LotoRepo lotoRepo;

    public LotoService(FileStorageService fileStorageService, LotoRepo lotoRepo) {
        this.fileStorageService = fileStorageService;
        this.lotoRepo = lotoRepo;
    }

    public LotoDto registrationLoto(Loto loto) {
        try {
            lotoRepo.save(loto);
        } catch (RuntimeException e) {
            log.error("ERROR save LOTO: " + loto, e);
        }
        LotoDto lotoDto = new LotoDto(
                loto.getId(),
                fileStorageService.getFileDownloadUri(loto.getFotoName()),
                loto.getDateTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"))
        );
        log.info("registration LOTO: " + loto);
        return lotoDto;
    }

    public Loto createLoto(String dataStart, Boolean complexBlocking, String fileName, String workSite, String specialistFIO) {
        Loto loto = new Loto();
        try {
            loto.setDateTime(LocalDateTime.now());
            loto.setDataStart(new SimpleDateFormat("yyyy-MM-dd").parse(dataStart));
            loto.setComplexBlocking(complexBlocking);
            loto.setFotoName(fileName);
            loto.setWorkSite(workSite);
            loto.setSpecialistFIO(specialistFIO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return loto;
    }
}
