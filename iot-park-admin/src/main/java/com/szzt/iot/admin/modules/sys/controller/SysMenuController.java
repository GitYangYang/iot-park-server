/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.sys.controller;

import com.szzt.iot.admin.modules.sys.dto.SysMenuDTO;
import com.szzt.iot.admin.modules.sys.enums.MenuTypeEnum;
import com.szzt.iot.admin.modules.sys.service.SysMenuService;
import com.szzt.iot.admin.modules.security.service.ShiroService;
import com.szzt.iot.admin.modules.security.user.SecurityUser;
import com.szzt.iot.admin.modules.security.user.UserDetail;
import com.szzt.iot.admin.common.annotation.LogOperation;
import com.szzt.iot.common.exception.ErrorCode;
import com.szzt.iot.common.utils.Result;
import com.szzt.iot.common.validator.AssertUtils;
import com.szzt.iot.common.validator.ValidatorUtils;
import com.szzt.iot.common.validator.group.DefaultGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 * 
 * @author
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags="菜单管理")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private ShiroService shiroService;

	@GetMapping("nav")
	@ApiOperation("导航")
	public Result<List<SysMenuDTO>> nav(){
		UserDetail user = SecurityUser.getUser();
		List<SysMenuDTO> list = sysMenuService.getUserMenuList(user, MenuTypeEnum.MENU.value());

		return new Result<List<SysMenuDTO>>().ok(list);
	}

	@GetMapping("permissions")
	@ApiOperation("权限标识")
	public Result<Set<String>> permissions(){
		UserDetail user = SecurityUser.getUser();
		Set<String> set = shiroService.getUserPermissions(user);

		return new Result<Set<String>>().ok(set);
	}

	@GetMapping("list")
	@ApiOperation("列表")
	@ApiImplicitParam(name = "type", value = "菜单类型 0：菜单 1：按钮  null：全部", paramType = "query", dataType="int")
	@RequiresPermissions("sys:menu:list")
	public Result<List<SysMenuDTO>> list(Integer type){
		List<SysMenuDTO> list = sysMenuService.getAllMenuList(type);

		return new Result<List<SysMenuDTO>>().ok(list);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@RequiresPermissions("sys:menu:info")
	public Result<SysMenuDTO> get(@PathVariable("id") Long id){
		SysMenuDTO data = sysMenuService.get(id);

		return new Result<SysMenuDTO>().ok(data);
	}

	@PostMapping
	@ApiOperation("保存")
	@LogOperation("保存")
	@RequiresPermissions("sys:menu:save")
	public Result save(@RequestBody SysMenuDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, DefaultGroup.class);

		sysMenuService.save(dto);

		return new Result();
	}

	@PutMapping
	@ApiOperation("修改")
	@LogOperation("修改")
	@RequiresPermissions("sys:menu:update")
	public Result update(@RequestBody SysMenuDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, DefaultGroup.class);

		sysMenuService.update(dto);

		return new Result();
	}

	@DeleteMapping("{id}")
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("sys:menu:delete")
	public Result delete(@PathVariable("id") Long id){
		//效验数据
		AssertUtils.isNull(id, "id");

		//判断是否有子菜单或按钮
		List<SysMenuDTO> list = sysMenuService.getListPid(id);
		if(list.size() > 0){
			return new Result().error(ErrorCode.SUB_MENU_EXIST);
		}

		sysMenuService.delete(id);

		return new Result();
	}

	@GetMapping("select")
	@ApiOperation("角色菜单权限")
	@RequiresPermissions("sys:menu:select")
	public Result<List<SysMenuDTO>> select(){
		UserDetail user = SecurityUser.getUser();
		List<SysMenuDTO> list = sysMenuService.getUserMenuList(user, null);

		return new Result<List<SysMenuDTO>>().ok(list);
	}
}