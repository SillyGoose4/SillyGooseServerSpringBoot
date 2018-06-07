package com.sillygoose.service.Mapper;

import com.sillygoose.service.Model.Goose;

public interface GooseMapper {

    int insert(Goose record);

    int insertSelective(Goose record);

    Goose selectById(Integer userId);

    int updateById(Goose record);

    int updateBySelective(Goose record);
}
