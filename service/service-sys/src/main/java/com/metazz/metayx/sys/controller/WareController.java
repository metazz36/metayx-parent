package com.metazz.metayx.sys.controller;

import com.metazz.metayx.common.result.Result;
import com.metazz.metayx.model.sys.Ware;
import com.metazz.metayx.sys.service.WareService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/admin/sys/ware")
@CrossOrigin
public class WareController {

    @Autowired
    private WareService wareService;

    @ApiOperation("查询所有仓库列表")
    @GetMapping("findAllList")
    public Result findAllList() {
        List<Ware> list = wareService.list();
        return Result.ok(list);
    }

}

