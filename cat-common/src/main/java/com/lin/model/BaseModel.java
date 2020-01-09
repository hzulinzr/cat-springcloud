package com.lin.model;

import lombok.Data;

import java.io.Serializable;

/**
 * model模版类 用于继承
 * @author hzh
 */
@Data
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 6373642136455614423L;

	/**
	 * 创建时间
	 */
	private Long createTime;
	/**
	 * 创建人
	 */
	private Long createUserId;
	/**
	 * 修改时间
	 */
	private Long modifyTime;
	/**
	 * 修改人
	 */
	private Long modifyUserId;
	/**
	 * 状态 1:有效 0:删除
	 */
	private Integer state;
}
