package com.os.entity.fileupload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileUpLoad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6849058590636336328L;
	private Long id;// 主键 id
	private String name;//用户名
	private Date createTime;//上传时间
	private String url;//访问路径
}
