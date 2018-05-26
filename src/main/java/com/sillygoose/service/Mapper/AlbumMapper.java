package com.sillygoose.service.Mapper;

import com.sillygoose.service.Model.Album;

import java.util.List;

public interface AlbumMapper {

    int insert(Album record);

    List<Album> selectByUserId(int userId);

    List<Album> selectByPicId(int picId);
}
