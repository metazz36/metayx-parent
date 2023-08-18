package com.metazz.metayx.acl.controller;

import com.metazz.metayx.common.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.metazz.metayx.acl.service.AdminService;
import com.metazz.metayx.acl.service.RoleService;
import com.metazz.metayx.common.utils.MD5;
import com.metazz.metayx.model.acl.Admin;
import com.metazz.metayx.vo.acl.AdminQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @ApiOperation("为用户进行角色分配")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long adminId,
                           @RequestParam Long[] roleId) {
        roleService.saveAdminRole(adminId,roleId);
        return Result.ok(null);
    }

    @ApiOperation("获取用户角色")
    @GetMapping("toAssign/{adminId}")
    public Result toAssign(@PathVariable Long adminId) {
       Map<String,Object> map  = roleService.getRoleByAdminId(adminId);
       return Result.ok(map);
    }

    @ApiOperation("用户列表")
    @GetMapping("{current}/{limit}")
    public Result list(@PathVariable Long current,
                       @PathVariable Long limit,
                       AdminQueryVo adminQueryVo) {
        Page<Admin> pageParam = new Page<Admin>(current,limit);
        IPage<Admin> pageModel = adminService.selectPageUser(pageParam,adminQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("根据id查询")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return Result.ok(admin);
    }

    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody Admin admin) {
        String password = admin.getPassword();
        String passwordMD5 = MD5.encrypt(password);
        admin.setPassword(passwordMD5);
        adminService.save(admin);
        return Result.ok(null);
    }

    @ApiOperation("修改用户")
    @PutMapping("update")
    public Result update(@RequestBody Admin admin) {
        adminService.updateById(admin);
        return Result.ok(null);
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        adminService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        adminService.removeByIds(idList);
        return Result.ok(null);
    }

}
