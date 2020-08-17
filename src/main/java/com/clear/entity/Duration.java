package com.clear.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
@TableName("duration")
public class Duration implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer durationid;

    private String name;

    private String aqscode;

    private String second;

    public Integer getDurationid() {
        return durationid;
    }

    public void setDurationid(Integer durationid) {
        this.durationid = durationid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAqscode() {
        return aqscode;
    }

    public void setAqscode(String aqscode) {
        this.aqscode = aqscode;
    }
    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Duration{" +
            "durationid=" + durationid +
            ", name=" + name +
            ", aqscode=" + aqscode +
            ", second=" + second +
        "}";
    }
}
