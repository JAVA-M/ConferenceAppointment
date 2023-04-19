package com.mxf.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author manxingfu
 * @date 2023/1/5
 * 会议室查询条件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CRQueryCondition implements Serializable {
    // 页号
    private Integer page;
    // 每页大小
    private Integer size;
    // 会议室类型
    private Integer type;
    // 会议室名称
    private String roomName;
    // 根据ID排序
    private Boolean idSort;
    // 根据面积排序
    private Boolean areaSort;
    // 根据容纳人数排序
    private Boolean capacitySort;
}
