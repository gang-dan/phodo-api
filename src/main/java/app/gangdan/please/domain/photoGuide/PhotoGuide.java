package app.gangdan.please.domain.photoGuide;

import app.gangdan.please.domain.BaseEntity;
import app.gangdan.please.domain.file.guide.GuideFile;
import app.gangdan.please.domain.hashtag.Hashtag;
import app.gangdan.please.domain.heart.Heart;
import app.gangdan.please.domain.image.contour.ContourImage;
import app.gangdan.please.domain.image.contourTrans.ContourTransImage;
import app.gangdan.please.domain.image.guide.GuideImage;
import app.gangdan.please.domain.image.mask.MaskImage;
import app.gangdan.please.domain.image.original.OriginalImage;
import app.gangdan.please.domain.member.Member;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import app.gangdan.please.dto.photoGuide.request.PhotoGuideSegRequestDtoV2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photo_guide")
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class PhotoGuide extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoGuideId;

    private String photoGuideName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_spot_id")
    private PhotoSpot photoSpot;

    @Builder.Default
    @OneToMany(mappedBy = "photoGuide", fetch = FetchType.LAZY)
    private List<Hashtag> hashtagList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "photoGuide", fetch = FetchType.LAZY)
    private List<Heart> heartList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "photoGuide", fetch = FetchType.LAZY)
    private List<OriginalImage> originalImageList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "photoGuide", fetch = FetchType.LAZY)
    private List<ContourImage> contourImageList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "photoGuide", fetch = FetchType.LAZY)
    private List<ContourTransImage> contourTransImageList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "photoGuide", fetch = FetchType.LAZY)
    private List<GuideImage> guideImageList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "photoGuide", fetch = FetchType.LAZY)
    private List<MaskImage> maskImageList = new ArrayList<>();

//    @Builder.Default
//    @OneToMany(mappedBy = "photoGuide", fetch = FetchType.LAZY)
//    private List<GuideFile> guideFileList = new ArrayList<>();

    @Lob
    @Column(name = "guide_line")
    private String guideLine;

    public void updateGuideLine(String guideLine) {
        this.guideLine = guideLine;
    }


    public static PhotoGuide create(PhotoSpot photoSpot, Member member){

        return PhotoGuide.builder()
                .member(member)
                .photoSpot(photoSpot)
                .build();
    }

    public static PhotoGuide createV2(PhotoGuideSegRequestDtoV2 req, PhotoSpot photoSpot, Member member){

        return PhotoGuide.builder()
                .member(member)
                .photoSpot(photoSpot)
                .guideLine(req.getGuideLine())
                .build();
    }

}
