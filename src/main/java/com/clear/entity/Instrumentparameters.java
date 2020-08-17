package com.clear.entity;

import java.math.BigDecimal;
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
@TableName("instrumentparameters")
public class Instrumentparameters implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer instrumentid;

    private Integer parameterid;

    private Integer timecorrection;

    private String method;

    private String poc;

    private BigDecimal maxdoubt;

    private BigDecimal mindoubt;

    private BigDecimal maxinvalid;

    private BigDecimal mininvalid;

    private String unit;

    public Integer getInstrumentid() {
        return instrumentid;
    }

    public void setInstrumentid(Integer instrumentid) {
        this.instrumentid = instrumentid;
    }
    public Integer getParameterid() {
        return parameterid;
    }

    public void setParameterid(Integer parameterid) {
        this.parameterid = parameterid;
    }
    public Integer getTimecorrection() {
        return timecorrection;
    }

    public void setTimecorrection(Integer timecorrection) {
        this.timecorrection = timecorrection;
    }
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public String getPoc() {
        return poc;
    }

    public void setPoc(String poc) {
        this.poc = poc;
    }
    public BigDecimal getMaxdoubt() {
        return maxdoubt;
    }

    public void setMaxdoubt(BigDecimal maxdoubt) {
        this.maxdoubt = maxdoubt;
    }
    public BigDecimal getMindoubt() {
        return mindoubt;
    }

    public void setMindoubt(BigDecimal mindoubt) {
        this.mindoubt = mindoubt;
    }
    public BigDecimal getMaxinvalid() {
        return maxinvalid;
    }

    public void setMaxinvalid(BigDecimal maxinvalid) {
        this.maxinvalid = maxinvalid;
    }
    public BigDecimal getMininvalid() {
        return mininvalid;
    }

    public void setMininvalid(BigDecimal mininvalid) {
        this.mininvalid = mininvalid;
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Instrumentparameters{" +
            "instrumentid=" + instrumentid +
            ", parameterid=" + parameterid +
            ", timecorrection=" + timecorrection +
            ", method=" + method +
            ", poc=" + poc +
            ", maxdoubt=" + maxdoubt +
            ", mindoubt=" + mindoubt +
            ", maxinvalid=" + maxinvalid +
            ", mininvalid=" + mininvalid +
            ", unit=" + unit +
        "}";
    }
}
