package com.metazz.metayx.acl.controller;

import com.metazz.metayx.common.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.metazz.metayx.acl.service.RoleService;
import com.metazz.metayx.model.acl.Role;
import com.metazz.metayx.vo.acl.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "角色接口")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("角色条件分页查询")
    @GetMapping("{current}/{limit}")
    public Result pageList(@PathVariable Long current,
                           @PathVariable Long limit,
                           RoleQueryVo roleQueryVo) { // roleQueryVo 前端用params发送
        Page<Role> pageParam = new Page<>(current,limit);
        IPage<Role> pageModel = roleService.selectRolePage(pageParam,roleQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("根据id查询角色")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role) { // @RequestBody role 前端用data发送
        boolean is_success = roleService.save(role);
        if(is_success) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation("修改角色")
    @PutMapping("update")
    public Result update(@RequestBody Role role) {
        roleService.updateById(role);
        return Result.ok(null);
    }

    @ApiOperation("根据id删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        roleService.removeByIds(idList);
        return Result.ok(null);
    }

}
