/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.szzt.iot.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 邮件发送记录
 * 
 * @author
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_mail_log")
public class SysMailLogEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 邮件模板ID
	 */
	private Long templateId;
	/**
	 * 发送者
	 */
	private String mailFrom;
	/**
	 * 收件人
	 */
	private String mailTo;
	/**
	 * 抄送者
	 */
	private String mailCc;
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件正文
	 */
	private String content;
	/**
	 * 发送状态  0：失败  1：成功
	 */
	private Integer status;

}