package com.comtop.topcloud.mapper;

import com.comtop.topcloud.model.Secret;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecretMapper {

    List<Secret> getAll();

    Secret getOne(String id);

    int insert(Secret secret);

    int update(Secret secret);

    int delete(String id);
}
