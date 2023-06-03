package app.gangdan.please.domain.hashtag;

import app.gangdan.please.domain.BaseEntity;
import app.gangdan.please.domain.member.Member;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "hashtag")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashtagId;

    private String hashtagName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_guide_id")
    private PhotoGuide photoGuide;


    public static Hashtag create(PhotoGuide photoGuide, String hashtagName){

        final Hashtag hashtag = Hashtag.builder()
                .photoGuide(photoGuide)
                .hashtagName(hashtagName)
                .build();
        return hashtag;
    }
}
