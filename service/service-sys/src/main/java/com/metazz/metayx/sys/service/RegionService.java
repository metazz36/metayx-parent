package com.metazz.metayx.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metayx.model.sys.Region;
import java.util.List;

public interface RegionService extends IService<Region> {

    // 根据区域关键字查询区域列表信息
    List<Region> getRegionByKeyword(String keyword);

}
