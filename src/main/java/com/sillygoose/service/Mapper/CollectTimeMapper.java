package com.sillygoose.service.Mapper;

import com.sillygoose.service.Model.CollectTime;

public interface CollectTimeMapper {
    int insert(CollectTime record);

    int insertSelective(CollectTime record);

    CollectTime selectById(int userId);

    //int update
}
