package com.metazz.metayx.sys.controller;

import com.metazz.metayx.common.result.Result;
import com.metazz.metayx.model.sys.Region;
import com.metazz.metayx.sys.service.RegionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/sys/region")
@CrossOrigin
public class RegionController {

    @Autowired
    private RegionService regionService;

    @ApiOperation("根据区域关键字查询区域列表信息")
    @GetMapping("findRegionByKeyword/{keyword}")
    public Result findRegionByKeyword(@PathVariable("keyword") String keyword) {
        List<Region> list = regionService.getRegionByKeyword(keyword);
        return Result.ok(list);
    }

}

