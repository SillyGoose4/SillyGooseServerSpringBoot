package com.sillygoose.service.Mapper;

import com.sillygoose.service.Model.Pic;

import java.util.List;

public interface PicMapper {

    int insert(Pic record);

    int insertSelective(Pic record);

    List<Pic> selectById(Integer picId);

    List<Pic> selectByLevel(String piclevel);

    List<Pic> selectByBelong(String picBelong);

}
