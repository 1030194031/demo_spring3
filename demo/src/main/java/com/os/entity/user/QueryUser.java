package com.os.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6849058590636336328L;
	private Long id;// 主键 id
	private String nickname;//用户名
	private String pic;//头像
	private String content;//用户信息
}
