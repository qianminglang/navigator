package com.clear.param.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName UserInfoOut
 *
 * @author qml
 * Date 2020/8/13 13:33
 * Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteOut {
    private String stationcode;
    private String name;
    private String statecode;
    private String elevation;
    private String latitude;
    private String longitude;
    private String utcoffset;
    private String aqscode;
    private String fullaqscode;
    private String isactive;
    private String isapproved;
    private String intlcode;
    private String ismobile;
    private String modified;
    private String picname;
    private String orderid;
}