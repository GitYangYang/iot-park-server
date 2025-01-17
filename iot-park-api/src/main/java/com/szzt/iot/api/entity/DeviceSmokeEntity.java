package com.szzt.iot.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 烟雾报警数据
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeviceSmokeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 备注名称
     */
    @ApiModelProperty(value = "备注名称")
    private String deviceName;
    /**
     * 状态/启用状态
     */
    @ApiModelProperty(value = "设备状态")
    private Integer deviceStatus;
    /**
     * 电池电压
     */
    @ApiModelProperty(value = "电池电压")
    private Double batteryVoltage;
    /**
     * 设备数据生成时间戳
     */
    @ApiModelProperty(value = "设备数据生成时间戳")
    private Date eventTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    /**
     * 设备唯一标示
     */
    @ApiModelProperty(value = "设备唯一标示")
    private String iotId;
    /**
     * 产品唯一标示
     */
    @ApiModelProperty(value = "产品唯一标示")
    private String productKey;
    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private String type;
    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;
    /**
     * 烟雾检测状态
     */
    @ApiModelProperty(value = "烟雾检测状态")
    private Integer smokeSensorState;


}