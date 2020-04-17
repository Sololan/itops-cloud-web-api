package com.wejoyclass.itops.cloud.dto;

import com.wejoyclass.uc.entity.DictItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 9:09 2020/2/19
 * @Modified By:
 **/
@Getter
@Setter
public class DictGroupDto {
    private Long id;
    private String name;
    private String groupKey;
    private Integer sort;
    private String description;
    private Integer isEdit;
    private Long orgId;
    private List<DictItem> dictItemList;
}
