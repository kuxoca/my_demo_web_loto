package com.kuxoca.loto.property;

import com.kuxoca.loto.entity.WorkSite;
import com.kuxoca.loto.repository.WorkSitesRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Log4j2
public class ConfigurationApp {
    private final WorkSitesRepo workSitesRepo;

    public ConfigurationApp(WorkSitesRepo workSitesRepo) {
        this.workSitesRepo = workSitesRepo;
    }

    @PostConstruct
    private void init() {
        try {
            if (workSitesRepo.findAll().isEmpty()) {
                createWorkSiteInDataBase();
            }
        } catch (RuntimeException e) {
            log.error("ERROR create sites...", e);
        }
    }

    private void createWorkSiteInDataBase() {
        workSitesRepo.save(new WorkSite("Фасовка"));
        workSitesRepo.save(new WorkSite("Процесс"));
        workSitesRepo.save(new WorkSite("Склад ЗЧ"));
        workSitesRepo.save(new WorkSite("Электрика"));//
        workSitesRepo.save(new WorkSite("Территория"));
        workSitesRepo.save(new WorkSite("ССиМ"));
        workSitesRepo.save(new WorkSite("СГП"));
        workSitesRepo.save(new WorkSite("Пилотный завод"));//
        workSitesRepo.save(new WorkSite("Очистные сооружения"));
        workSitesRepo.save(new WorkSite("Лаборатория физхим"));
        workSitesRepo.save(new WorkSite("Копакер"));
        workSitesRepo.save(new WorkSite("Лаборатория микроб"));
        workSitesRepo.save(new WorkSite("Котельная"));
        workSitesRepo.save(new WorkSite("АХУ"));
    }
}
