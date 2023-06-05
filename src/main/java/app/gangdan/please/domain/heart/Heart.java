package app.gangdan.please.domain.heart;

import app.gangdan.please.domain.member.Member;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "heart")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;      // TODO : 연관관계 점검

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_guide_id")
    private PhotoGuide photoGuide;

    public static Heart create(PhotoGuide photoGuide, Member member) {
        final Heart heart = Heart.builder()
                .photoGuide(photoGuide)
                .member(member)
                .build();
        photoGuide.getHeartList().add(heart);
        return heart;
    }

    public boolean isCreator(Long memberId) {
        return this.member.getMemberId().equals(memberId);
    }

}
