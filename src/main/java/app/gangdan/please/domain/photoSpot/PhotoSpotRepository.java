package app.gangdan.please.domain.photoSpot;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoSpotRepository extends JpaRepository<PhotoSpot, Long>, PhotoSpotCustomRepository {

    @Query("select ps from PhotoSpot ps where ps.photoSpotName =:photoSpotName")
    PhotoSpot findByName(@Param("photoSpotName") String photoSpotName);
}
