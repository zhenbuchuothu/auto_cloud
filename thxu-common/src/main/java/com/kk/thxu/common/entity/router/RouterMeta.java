package com.kk.thxu.common.entity.router;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {
    private static final long serialVersionUID = 4096926728347831326L;
    /**
     * 标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
}
