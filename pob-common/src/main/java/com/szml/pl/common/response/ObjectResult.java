package com.szml.pl.common.response;

import java.io.Serializable;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/23
 */
public class ObjectResult<T> extends Result implements Serializable {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
