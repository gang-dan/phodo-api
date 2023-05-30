package app.gangdan.please.service.hashtag;

import app.gangdan.please.domain.hashtag.Hashtag;
import app.gangdan.please.domain.hashtag.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public List<Hashtag> getHashtags(Long photoSpotId){

        return hashtagRepository.findByPhotoSpotId(photoSpotId);
    }
}
