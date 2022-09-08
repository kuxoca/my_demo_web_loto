package com.kuxoca.loto.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity(name = "LOTONd")
@Table(name = "LOTONd")
@AllArgsConstructor
@NoArgsConstructor
public class Loto extends AbstractEntity {
    private LocalDateTime dateTime;
    private String specialistFIO;
    private Date dataStart;
    private Boolean complexBlocking;
    private String workSite;
    private String fotoName;
}