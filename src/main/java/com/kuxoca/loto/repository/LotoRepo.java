package com.kuxoca.loto.repository;


import com.kuxoca.loto.entity.Loto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotoRepo extends JpaRepository<Loto, Long> {
}
