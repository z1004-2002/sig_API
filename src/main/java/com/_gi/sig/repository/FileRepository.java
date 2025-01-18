package com._gi.sig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com._gi.sig.models.FileDto;

import java.util.List;
import java.util.UUID;


@Repository
public interface FileRepository extends JpaRepository<FileDto, Long> {
    @Query("select i from FileDto i where i.idBureau = ?1 ")
	List<FileDto> findByIdBureau(UUID idBureau);
}
