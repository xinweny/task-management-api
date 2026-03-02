package com.slic.task_management_api.mapper;

public interface Mapper<E, D> {
    D toDTO(E entity);
    E toEntity(D dto);
}
