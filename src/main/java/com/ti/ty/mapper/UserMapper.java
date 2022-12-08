package com.ti.ty.mapper;


import com.ti.ty.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUsers(@Param("text") String text);
}
