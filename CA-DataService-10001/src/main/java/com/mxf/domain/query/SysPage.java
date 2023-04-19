package com.mxf.domain.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.util.List;

/**
 * @author manxingfu
 * @date 2023/3/22
 */
public class SysPage<T> implements IPage<T> {

    public SysPage() {
    }

    @Override
    public List<OrderItem> orders() {
        return null;
    }

    @Override
    public List getRecords() {
        return null;
    }

    @Override
    public IPage setRecords(List records) {
        return null;
    }

    @Override
    public long getTotal() {
        return 0;
    }

    @Override
    public IPage setTotal(long total) {
        return null;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public IPage setSize(long size) {
        return null;
    }

    @Override
    public long getCurrent() {
        return 0;
    }

    @Override
    public IPage setCurrent(long current) {
        return null;
    }
}
