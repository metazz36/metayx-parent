package com.metazz.metayx.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metayx.model.sys.Ware;
import com.metazz.metayx.sys.mapper.WareMapper;
import com.metazz.metayx.sys.service.WareService;
import org.springframework.stereotype.Service;

@Service
public class WareServiceImpl extends ServiceImpl<WareMapper, Ware> implements WareService {

}
