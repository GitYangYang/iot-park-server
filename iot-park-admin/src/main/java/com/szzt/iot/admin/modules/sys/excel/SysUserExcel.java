/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.sys.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 用户管理
 *
 * @author
 * @since 1.0.0
 */
@Data
public class SysUserExcel {
    @Excel(name = "用户名")
    private String username;
    @Excel(name = "姓名")
    private String realName;
    @Excel(name = "性别", replace = {"男_0", "女_1", "保密_2"})
    private Integer gender;
    @Excel(name = "邮箱")
    private String email;
    @Excel(name = "手机号")
    private String mobile;
    @Excel(name = "部门名称")
    private String deptName;
    @Excel(name = "状态", replace = {"停用_0", "正常_1"})
    private Integer status;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}
