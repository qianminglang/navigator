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
@TableName("parameter")
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer parameterid;

    private String name;

    private String ingestcode;

    private Integer unitid;

    private BigDecimal scale;

    private String aqscode;

    private Boolean isalpha;

    private Boolean hourly;

    private Integer hourlypercent;

    private Boolean isaqsexport;

    private Boolean isaqsimport;

    private String color;

    private String abbr;

    private String charttype;

    private String type;

    private Integer orderid;

    private Integer order;

    private Integer ekmaid;

    private String unit;

    private Integer digits;

    private String soap;

    private String initunit;

    public Integer getParameterid() {
        return parameterid;
    }

    public void setParameterid(Integer parameterid) {
        this.parameterid = parameterid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getIngestcode() {
        return ingestcode;
    }

    public void setIngestcode(String ingestcode) {
        this.ingestcode = ingestcode;
    }
    public Integer getUnitid() {
        return unitid;
    }

    public void setUnitid(Integer unitid) {
        this.unitid = unitid;
    }
    public BigDecimal getScale() {
        return scale;
    }

    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }
    public String getAqscode() {
        return aqscode;
    }

    public void setAqscode(String aqscode) {
        this.aqscode = aqscode;
    }
    public Boolean getIsalpha() {
        return isalpha;
    }

    public void setIsalpha(Boolean isalpha) {
        this.isalpha = isalpha;
    }
    public Boolean getHourly() {
        return hourly;
    }

    public void setHourly(Boolean hourly) {
        this.hourly = hourly;
    }
    public Integer getHourlypercent() {
        return hourlypercent;
    }

    public void setHourlypercent(Integer hourlypercent) {
        this.hourlypercent = hourlypercent;
    }
    public Boolean getIsaqsexport() {
        return isaqsexport;
    }

    public void setIsaqsexport(Boolean isaqsexport) {
        this.isaqsexport = isaqsexport;
    }
    public Boolean getIsaqsimport() {
        return isaqsimport;
    }

    public void setIsaqsimport(Boolean isaqsimport) {
        this.isaqsimport = isaqsimport;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    public String getCharttype() {
        return charttype;
    }

    public void setCharttype(String charttype) {
        this.charttype = charttype;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    public Integer getEkmaid() {
        return ekmaid;
    }

    public void setEkmaid(Integer ekmaid) {
        this.ekmaid = ekmaid;
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Integer getDigits() {
        return digits;
    }

    public void setDigits(Integer digits) {
        this.digits = digits;
    }
    public String getSoap() {
        return soap;
    }

    public void setSoap(String soap) {
        this.soap = soap;
    }
    public String getInitunit() {
        return initunit;
    }

    public void setInitunit(String initunit) {
        this.initunit = initunit;
    }

    @Override
    public String toString() {
        return "Parameter{" +
            "parameterid=" + parameterid +
            ", name=" + name +
            ", ingestcode=" + ingestcode +
            ", unitid=" + unitid +
            ", scale=" + scale +
            ", aqscode=" + aqscode +
            ", isalpha=" + isalpha +
            ", hourly=" + hourly +
            ", hourlypercent=" + hourlypercent +
            ", isaqsexport=" + isaqsexport +
            ", isaqsimport=" + isaqsimport +
            ", color=" + color +
            ", abbr=" + abbr +
            ", charttype=" + charttype +
            ", type=" + type +
            ", orderid=" + orderid +
            ", order=" + order +
            ", ekmaid=" + ekmaid +
            ", unit=" + unit +
            ", digits=" + digits +
            ", soap=" + soap +
            ", initunit=" + initunit +
        "}";
    }
}
