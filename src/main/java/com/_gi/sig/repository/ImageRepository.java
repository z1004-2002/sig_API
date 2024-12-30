package com._gi.sig.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com._gi.sig.models.Image;


@Repository
public interface ImageRepository extends JpaRepository<Image, UUID>{
	@Query("SELECT i FROM Image i WHERE i.idBureau = ?1 ")
	List<Image> findByIdRessource(String idBureau);
}
