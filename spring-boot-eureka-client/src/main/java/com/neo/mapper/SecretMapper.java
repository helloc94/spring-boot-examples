package com.neo.mapper;

import com.neo.model.Secret;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecretMapper {

    List<Secret> getAll();

    Secret getOne(String id);

    void insert(Secret secret);

    void update(Secret secret);

    void delete(String id);
}
