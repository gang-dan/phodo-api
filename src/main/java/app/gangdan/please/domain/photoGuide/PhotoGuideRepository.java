package app.gangdan.please.domain.photoGuide;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoGuideRepository extends JpaRepository<PhotoGuide, Long> {

    @Query("select pg from PhotoGuide  pg where pg.photoSpot.photoSpotId =:photoSpotId")
    List<PhotoGuide> findByPhotoSpotId(@Param(value = "photoSpotId") Long photoSpotId);
}
