package com.kuxoca.loto.controller;

import com.kuxoca.loto.entity.Loto;
import com.kuxoca.loto.entity.LotoDto;
import com.kuxoca.loto.entity.WorkSite;
import com.kuxoca.loto.repository.LotoRepo;
import com.kuxoca.loto.repository.WorkSitesRepo;
import com.kuxoca.loto.service.FileStorageService;
import com.kuxoca.loto.service.LotoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Controller
public class LotoController {

    private final WorkSitesRepo workSitesRepo;
    private final LotoRepo lotoRepo;
    private final FileStorageService fileStorageService;
    private final LotoService lotoService;

    public LotoController(WorkSitesRepo workSitesRepo, LotoRepo lotoRepo, FileStorageService fileStorageService, LotoService lotoService) {
        this.workSitesRepo = workSitesRepo;
        this.lotoRepo = lotoRepo;
        this.fileStorageService = fileStorageService;
        this.lotoService = lotoService;
    }

    @GetMapping("/workpermit/loto")
    public String home(Model model) {
        model.addAttribute("title", "Safety_LOTO_ND");
        Iterable<WorkSite> workSites = workSitesRepo.findAll();
        model.addAttribute("workSites", workSites);
        return "lotoTemplate";
    }

    @PostMapping("/workpermit/loto/regLOTO")
    public String regLoto(Model model,
                          @RequestParam String specialistFIO,
                          boolean complexBlocking,
                          String workSite,
                          String dataStart,
                          @RequestParam("file") MultipartFile file
    ) {
        if (!file.getContentType().contains("image") || !(file.getSize() > 100)) {
            model.addAttribute("error", "приложенный файл не являеться фото");
            return "lotoRegFalse";
        } else {
            try {

                String fileName = fileStorageService.storeFile(file);
                Loto loto = lotoService.createLoto(dataStart, complexBlocking, fileName, workSite, specialistFIO);
                LotoDto lotoDto = lotoService.registrationLoto(loto);
                model.addAttribute("lotoDto", lotoDto);
                return "lotoRegComplite";
            } catch (RuntimeException e) {
                log.error("ERROR ", e);
                return "lotoRegFalse";
            }
        }
    }

    @GetMapping("/workpermit/loto/allview")
    public String allLOTOND(Model model) {
        Iterable<Loto> lotoNds = lotoRepo.findAll();
        model.addAttribute("title", "Safety_LOTO_Permit");
        model.addAttribute("LOTONds", lotoNds);
        return "viewAllLoto";
    }

}
