package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 菜单表
 * @TableName pms_menu
 */
@TableName(value ="pms_menu")
@Data
public class PmsMenu implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 是否为叶节点
     */
    @TableField(value = "is_leaf")
    private String isLeaf;

    /**
     * 层级
     */
    @TableField(value = "level")
    private Integer level;

    /**
     * 父节点
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 编号
     */
    @TableField(value = "number")
    private String number;

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 菜单链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 状态 ACTIVE, DEACTIVE
     */
    @TableField(value = "status")
    private String status;

    /**
     * 修改时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}