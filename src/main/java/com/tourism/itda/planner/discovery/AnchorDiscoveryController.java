package com.tourism.itda.planner.discovery;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 작품 관련 명소 발굴 배치({@link AnchorDiscoveryBatch}) 수동 실행용 엔드포인트.
 *
 * <p><b>관리자 수동 실행용. 인증·권한 연결은 아직 안 됨.</b>
 * 운영 place/content_place 테이블에 직접 쓰기 때문에, 인증 미들웨어가 붙기 전까지는
 * 아는 사람만 호출해야 한다.
 */
@RestController
@RequestMapping("/api/admin/route")
@RequiredArgsConstructor
public class AnchorDiscoveryController {

    private final AnchorDiscoveryBatch anchorDiscoveryBatch;

    @PostMapping("/discover-anchors")
    public DiscoveryReport discoverAnchors(
            @RequestParam(name = "only_missing", defaultValue = "true") boolean onlyMissing,
            @RequestParam(name = "max_contents", defaultValue = "10") int maxContents) {
        return anchorDiscoveryBatch.run(onlyMissing, maxContents);
    }
}
