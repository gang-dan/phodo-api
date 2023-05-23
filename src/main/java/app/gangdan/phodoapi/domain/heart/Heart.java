package app.gangdan.phodoapi.domain.heart;

import app.gangdan.phodoapi.domain.member.Member;
import app.gangdan.phodoapi.domain.photoGuide.PhotoGuide;
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

}
