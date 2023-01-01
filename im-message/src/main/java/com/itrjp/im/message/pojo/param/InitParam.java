package com.itrjp.im.message.pojo.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 15:01
 */
@Data
public class InitParam {
    @NotBlank(message = "用户ID不能为空")
    private String userId;
}
