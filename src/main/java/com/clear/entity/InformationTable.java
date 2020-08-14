package com.clear.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-14
 */
@TableName("information_table")
public class InformationTable implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer column1;

    private Integer column2;

    public Integer getColumn1() {
        return column1;
    }

    public void setColumn1(Integer column1) {
        this.column1 = column1;
    }
    public Integer getColumn2() {
        return column2;
    }

    public void setColumn2(Integer column2) {
        this.column2 = column2;
    }

    @Override
    public String toString() {
        return "InformationTable{" +
            "column1=" + column1 +
            ", column2=" + column2 +
        "}";
    }
}
