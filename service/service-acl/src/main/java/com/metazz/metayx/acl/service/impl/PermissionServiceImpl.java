package com.metazz.metayx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metayx.acl.mapper.PermissionMapper;
import com.metazz.metayx.acl.service.PermissionService;
import com.metazz.metayx.acl.utils.PermissionHelper;
import com.metazz.metayx.model.acl.Permission;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> queryAllPermission() {
        // 1 查询所有菜单
        List<Permission> allPermissionList = baseMapper.selectList(null);
        // 2 转换要求数据格式
        List<Permission> result = PermissionHelper.buildPermission(allPermissionList);
        return result;
    }

    @Override
    public void removeChildById(Long id) {
        // 创建list集合idList，封装所有要删除菜单id
        List<Long> idList = new ArrayList<>();
        this.getAllPermissionId(id,idList);
        idList.add(id);
        // 调用方法根据多个菜单id删除
        baseMapper.deleteBatchIds(idList);
    }

    // 递归找当前菜单下面的所有子菜单
    // 第一个参数是当前菜单id,第二个参数最终封装list集合，包含所有菜单id
    private void getAllPermissionId(Long id, List<Long> idList) {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPid,id);
        List<Permission> childList = baseMapper.selectList(wrapper);
        // 递归查询是否还有子菜单，有继续递归查询
        childList.stream().forEach(item -> {
            // 封装菜单id到idList集合里面
            idList.add(item.getId());
            // 递归
            this.getAllPermissionId(item.getId(),idList);
        });
    }

}
