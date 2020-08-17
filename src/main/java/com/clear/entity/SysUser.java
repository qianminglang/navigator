package com.clear.entity;

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
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private String username;

    private String chinesename;

    private String userpwd;

    private String activestate;

    private String auditstate;

    private String smsstate;

    private String emailstate;

    private String sex;

    private String telephone;

    private String phone;

    private String email;

    private String description;

    private LocalDateTime createtime;

    private String createpreson;

    private LocalDateTime lastmodifytime;

    private String lastmodifypreson;

    private String groupid;

    private String departmentId;

    private String province;

    private Integer roleid;

    private String city;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getChinesename() {
        return chinesename;
    }

    public void setChinesename(String chinesename) {
        this.chinesename = chinesename;
    }
    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
    public String getActivestate() {
        return activestate;
    }

    public void setActivestate(String activestate) {
        this.activestate = activestate;
    }
    public String getAuditstate() {
        return auditstate;
    }

    public void setAuditstate(String auditstate) {
        this.auditstate = auditstate;
    }
    public String getSmsstate() {
        return smsstate;
    }

    public void setSmsstate(String smsstate) {
        this.smsstate = smsstate;
    }
    public String getEmailstate() {
        return emailstate;
    }

    public void setEmailstate(String emailstate) {
        this.emailstate = emailstate;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
    public String getCreatepreson() {
        return createpreson;
    }

    public void setCreatepreson(String createpreson) {
        this.createpreson = createpreson;
    }
    public LocalDateTime getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(LocalDateTime lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
    public String getLastmodifypreson() {
        return lastmodifypreson;
    }

    public void setLastmodifypreson(String lastmodifypreson) {
        this.lastmodifypreson = lastmodifypreson;
    }
    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "SysUser{" +
            "userid=" + userid +
            ", username=" + username +
            ", chinesename=" + chinesename +
            ", userpwd=" + userpwd +
            ", activestate=" + activestate +
            ", auditstate=" + auditstate +
            ", smsstate=" + smsstate +
            ", emailstate=" + emailstate +
            ", sex=" + sex +
            ", telephone=" + telephone +
            ", phone=" + phone +
            ", email=" + email +
            ", description=" + description +
            ", createtime=" + createtime +
            ", createpreson=" + createpreson +
            ", lastmodifytime=" + lastmodifytime +
            ", lastmodifypreson=" + lastmodifypreson +
            ", groupid=" + groupid +
            ", departmentId=" + departmentId +
            ", province=" + province +
            ", roleid=" + roleid +
            ", city=" + city +
        "}";
    }
}
