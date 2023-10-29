package app.gangdan.please.domain.image.guide;

import app.gangdan.please.domain.image.original.OriginalImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideImageRepository extends JpaRepository<GuideImage,Long> {
}
