package com.clear.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
@TableName("sail")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 走航编号
     */
    private Integer sailId;

    /**
     * 走航开始时间
     */
    private LocalDateTime startTime;

    /**
     * 开始操作用户id
     */
    private String startUserId;

    /**
     * 走航结束时间
     */
    private LocalDateTime endTime;

    /**
     * 结束操作用户id
     */
    private String endUserId;

    /**
     * 走行车编号
     */
    private String stationId;

    public Integer getSailId() {
        return sailId;
    }

    public void setSailId(Integer sailId) {
        this.sailId = sailId;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public String getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(String endUserId) {
        this.endUserId = endUserId;
    }
    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    @Override
    public String toString() {
        return "Sail{" +
            "sailId=" + sailId +
            ", startTime=" + startTime +
            ", startUserId=" + startUserId +
            ", endTime=" + endTime +
            ", endUserId=" + endUserId +
            ", stationId=" + stationId +
        "}";
    }
}
