package msaboard.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 사용자 Feign Client
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@FeignClient(name="user-service", url= "http://127.0.0.1:9000/user-service")
public interface UserFeignClient {
    
    /**
     * 사용자 상세 정보 조회
     * @param userId 사용자 아이디
     */
    @GetMapping("/v1/feign/getUserInfoDetail/{userId}")
    UserInfoDto getUserInfoDetail(@PathVariable("userId") String userId);
}
