package com.kuxoca.loto.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "work_sites")
@Table(name = "work_sites")
public class WorkSite extends AbstractEntity {
    private String workSite;
}
