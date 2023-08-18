package com.metazz.metayx.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metayx.model.sys.RegionWare;
import com.metazz.metayx.vo.sys.RegionWareQueryVo;

public interface RegionWareService extends IService<RegionWare> {

    // 开通区域列表
    IPage<RegionWare> selectPageRegionWare(Page<RegionWare> pageParam, RegionWareQueryVo regionWareQueryVo);

    // 添加开通区域
    void saveRegionWare(RegionWare regionWare);

    // 取消开通区域
    void updateStatus(Long id, Integer status);
}
