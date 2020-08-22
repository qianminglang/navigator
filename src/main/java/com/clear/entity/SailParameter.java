package com.clear.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
@TableName("sail_parameter")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SailParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务编号
     */
    private Integer sailId;

    /**
     * 因子列表
     */
    private String parameterid;

}
