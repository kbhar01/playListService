package com.cognizant.playlistService.repository;

import com.cognizant.playlistService.entity.PlayListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends JpaRepository<PlayListEntity, Long> {

}
