/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.szzt.iot.admin.modules.security.user.SecurityUser;
import com.szzt.iot.admin.modules.security.user.UserDetail;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 公共字段，自动填充值
 *
 * @author
 */
@Component
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_DATE = "createDate";
    private final static String CREATOR = "creator";
    private final static String UPDATE_DATE = "updateDate";
    private final static String UPDATER = "updater";
    private final static String DEPT_ID = "deptId";

    @Override
    public void insertFill(MetaObject metaObject) {
        UserDetail user = SecurityUser.getUser();
        Date date = new Date();

        //创建者
        setInsertFieldValByName(CREATOR, user.getId(), metaObject);
        //创建时间
        setInsertFieldValByName(CREATE_DATE, date, metaObject);

        //创建者所属部门
        setInsertFieldValByName(DEPT_ID, user.getDeptId(), metaObject);

        //更新者
        setInsertFieldValByName(UPDATER, user.getId(), metaObject);
        //更新时间
        setInsertFieldValByName(UPDATE_DATE, date, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新者
        setUpdateFieldValByName(UPDATER, SecurityUser.getUserId(), metaObject);
        //更新时间
        setUpdateFieldValByName(UPDATE_DATE, new Date(), metaObject);
    }
}