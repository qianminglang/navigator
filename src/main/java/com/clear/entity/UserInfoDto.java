package com.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName UserInfoDto
 *
 * @author qml
 * Date 2020/8/13 10:02
 * Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto {
    private String userName;
    private String password;
}