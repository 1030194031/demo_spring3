package com.os.entity.template;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Template implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6849058590636336328L;
	private Long id;// 主键 id
	private String content;//内容
}
