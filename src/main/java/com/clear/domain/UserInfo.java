package com.clear.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName UserInfo
 *
 * @author qml
 * Date 2020/8/13 9:59
 * Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    private String userName;
    private String password;
}