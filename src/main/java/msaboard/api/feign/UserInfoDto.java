package msaboard.api.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 사용자 DTO
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserInfoDto {

    private String userId;              //사용자 아아디

    private String userPw;              //사용자 비밀번호

    private String userNm;              //사용자 이름

    private String userBirth;           //사용자 생년월일

}