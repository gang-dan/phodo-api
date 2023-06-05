package app.gangdan.please.domain.file.guide;

import app.gangdan.please.domain.hashtag.HashtagCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideFileRepository extends JpaRepository<GuideFile, Long>, HashtagCustomRepository {

}
