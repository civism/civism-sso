package com.civism.sso.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Data
@TableName("sso_product")
public class SsoProductDO extends BaseDO {

    private static final long serialVersionUID = 5019247183721310811L;

    private String name;
}
