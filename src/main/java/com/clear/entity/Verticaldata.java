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
@TableName("verticaldata")
public class Verticaldata implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataid;

    private String stationcode;

    private Integer parameterid;

    private Integer durationid;

    private LocalDateTime utc;

    private LocalDateTime lst;

    private BigDecimal value;

    private Integer poc;

    private Integer opcode;

    private Integer qccode;

    private Integer frequencyid;

    private Integer instrumentid;

    private LocalDateTime lastuptime;

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }
    public String getStationcode() {
        return stationcode;
    }

    public void setStationcode(String stationcode) {
        this.stationcode = stationcode;
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
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public Integer getPoc() {
        return poc;
    }

    public void setPoc(Integer poc) {
        this.poc = poc;
    }
    public Integer getOpcode() {
        return opcode;
    }

    public void setOpcode(Integer opcode) {
        this.opcode = opcode;
    }
    public Integer getQccode() {
        return qccode;
    }

    public void setQccode(Integer qccode) {
        this.qccode = qccode;
    }
    public Integer getFrequencyid() {
        return frequencyid;
    }

    public void setFrequencyid(Integer frequencyid) {
        this.frequencyid = frequencyid;
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
        return "Verticaldata{" +
            "dataid=" + dataid +
            ", stationcode=" + stationcode +
            ", parameterid=" + parameterid +
            ", durationid=" + durationid +
            ", utc=" + utc +
            ", lst=" + lst +
            ", value=" + value +
            ", poc=" + poc +
            ", opcode=" + opcode +
            ", qccode=" + qccode +
            ", frequencyid=" + frequencyid +
            ", instrumentid=" + instrumentid +
            ", lastuptime=" + lastuptime +
        "}";
    }
}
