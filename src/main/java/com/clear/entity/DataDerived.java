package com.clear.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("data_derived")
public class DataDerived implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataid;

    private Integer parameterid;

    private Integer durationid;

    private BigDecimal value;

    /**
     * 衍生数据类型：0-最大voc、1-TVOC、2-最大OFP、3-最大SOAP
     */
    private Integer type;

    private LocalDateTime utc;

    private LocalDateTime lst;

    private Integer instrumentid;

    private LocalDateTime lastuptime;

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }
    public Integer getParameterid() {
        return parameterid;
    }

    public void setParameterid(Integer parameterid) {
        this.parameterid = parameterid;
    }
    public Integer getDurationid() {
        return durationid;
    }

    public void setDurationid(Integer durationid) {
        this.durationid = durationid;
    }
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public LocalDateTime getUtc() {
        return utc;
    }

    public void setUtc(LocalDateTime utc) {
        this.utc = utc;
    }
    public LocalDateTime getLst() {
        return lst;
    }

    public void setLst(LocalDateTime lst) {
        this.lst = lst;
    }
    public Integer getInstrumentid() {
        return instrumentid;
    }

    public void setInstrumentid(Integer instrumentid) {
        this.instrumentid = instrumentid;
    }
    public LocalDateTime getLastuptime() {
        return lastuptime;
    }

    public void setLastuptime(LocalDateTime lastuptime) {
        this.lastuptime = lastuptime;
    }

    @Override
    public String toString() {
        return "DataDerived{" +
            "dataid=" + dataid +
            ", parameterid=" + parameterid +
            ", durationid=" + durationid +
            ", value=" + value +
            ", type=" + type +
            ", utc=" + utc +
            ", lst=" + lst +
            ", instrumentid=" + instrumentid +
            ", lastuptime=" + lastuptime +
        "}";
    }
}
