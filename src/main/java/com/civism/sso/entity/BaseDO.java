package com.civism.sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Data
public class BaseDO implements Serializable {

    private static final long serialVersionUID = -5048221139649128719L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    @TableLogic
    private Integer grdDelete;
}
