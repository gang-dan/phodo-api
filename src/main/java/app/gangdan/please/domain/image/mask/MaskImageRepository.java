package app.gangdan.please.domain.image.mask;

import app.gangdan.please.domain.image.original.OriginalImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaskImageRepository extends JpaRepository<MaskImage,Long> {
}
