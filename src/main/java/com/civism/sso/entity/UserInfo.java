package com.civism.sso.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -2253372833687147328L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户token
     */
    private String token;
}
