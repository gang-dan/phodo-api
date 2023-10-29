package app.gangdan.please.domain.image.contourTrans;

import app.gangdan.please.domain.image.original.OriginalImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContourTransImageRepository extends JpaRepository<ContourTransImage,Long> {
}
