package app.gangdan.please.domain.photoSpot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoSpotRepository extends JpaRepository<PhotoSpot, Long>, PhotoSpotCustomRepository {

}
