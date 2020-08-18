package com.clear.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
@TableName("data")
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataid;

    private String stationcode;

    private Integer parameterid;

    private Integer durationid;

    private LocalDateTime utc;

    private LocalDateTime lst;

    private Integer poc;

    private BigDecimal value;

    private Integer opcode;

    private Integer qccode;

    private Integer frequencyid;

    private Integer instrumentid;

    private Date lastuptime;
}
