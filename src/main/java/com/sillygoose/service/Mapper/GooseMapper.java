package com.sillygoose.service.Mapper;

import com.sillygoose.service.Model.Goose;

import java.util.List;

public interface GooseMapper {

    int insert(Goose record);

    int insertSelective(Goose record);

    List<Goose> selectById(Integer userId);

    int updateById(Goose record);

    int updateBySelective(Goose record);
}
