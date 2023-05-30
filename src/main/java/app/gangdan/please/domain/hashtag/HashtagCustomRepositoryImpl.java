package app.gangdan.please.domain.hashtag;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.gangdan.please.domain.hashtag.QHashtag.hashtag;
import static app.gangdan.please.domain.photoGuide.QPhotoGuide.photoGuide;
import static app.gangdan.please.domain.photoSpot.QPhotoSpot.photoSpot;

@Repository
@RequiredArgsConstructor
@Slf4j
public class HashtagCustomRepositoryImpl implements HashtagCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Hashtag> findByPhotoSpotId(Long photoSpotId) {

        return jpaQueryFactory.select(hashtag)
                .from(hashtag)
                .innerJoin(hashtag.photoGuide, photoGuide)
                .innerJoin(photoGuide.photoSpot, photoSpot)
                .where(hashtag.photoGuide.photoSpot.photoSpotId.eq(photoSpotId))
                .fetch();
    }
}
