package com.clear.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
@TableName("instrument")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Instrument implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer instrumentid;

    private String name;

    private String instrumentno;

    private String instrumentpw;

    private String stationcode;

    private String model;

    private String manufacture;

    private String seriano;

    private Integer frequencyid;

    private Integer durationid;

    private LocalDateTime purchasedate;

    private LocalDateTime decommissiondate;

    private Boolean isactive;

    private String status;

    private LocalDateTime modified;

    private String tfclientip;

    private Integer mainorder;

    private String vendor;


}
