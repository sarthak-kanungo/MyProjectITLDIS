package com.i4o.dms.itldis.masters.usermanagement.user.dto;

public interface IElement<R> {

    R getId();

    R getParentId();

    default boolean checkId(R id) {
        return this.getId().equals(id);
    }
}
