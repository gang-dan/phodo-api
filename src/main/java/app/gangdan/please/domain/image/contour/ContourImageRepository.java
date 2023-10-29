package app.gangdan.please.domain.image.contour;

import app.gangdan.please.domain.image.original.OriginalImage;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContourImageRepository extends JpaRepository<ContourImage,Long> {
}
