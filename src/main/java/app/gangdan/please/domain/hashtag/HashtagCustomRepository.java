package app.gangdan.please.domain.hashtag;

import java.util.List;

public interface HashtagCustomRepository {

    List<Hashtag> findByPhotoSpotId(Long photoSpotId);
}
