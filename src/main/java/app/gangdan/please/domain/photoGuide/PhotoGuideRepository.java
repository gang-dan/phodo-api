package app.gangdan.please.domain.photoGuide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoGuideRepository extends JpaRepository<PhotoGuide, Long> {

}
