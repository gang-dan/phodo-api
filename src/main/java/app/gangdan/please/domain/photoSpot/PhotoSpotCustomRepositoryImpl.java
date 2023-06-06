package app.gangdan.please.domain.photoSpot;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.gangdan.please.domain.photoSpot.QPhotoSpot.photoSpot;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PhotoSpotCustomRepositoryImpl implements PhotoSpotCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PhotoSpot> getSpotList(double latitude, double longitude, double radius) {

        double metersToDegrees = 0.000008998719243599958; // 미터 단위의 반지름을 경도/위도 차이로 변환

        // 입력받은 위도, 경도를 기준으로 범위 계산
        double minLat = Math.round(latitude - (radius * metersToDegrees));
        double maxLat = Math.round(latitude + (radius * metersToDegrees));
        double minLng = Math.round(longitude - (radius * metersToDegrees / Math.cos(Math.toRadians(latitude))));
        double maxLng = Math.round(longitude + (radius * metersToDegrees / Math.cos(Math.toRadians(latitude))));

        log.info("minlat ::: " + minLat);
        log.info("maxlat ::: " + maxLat);
        log.info("minlng ::: " + minLng);
        log.info("maxlng ::: " + maxLng);


        BooleanExpression withinRadius = photoSpot.latitude.between(minLat, maxLat)
                .and(photoSpot.longitude.between(maxLng, minLng));

        return jpaQueryFactory.select(photoSpot)
                //.where(photoSpot.latitude.between(minLat, maxLat))
                .fetch();

        // return null;
    }
}
