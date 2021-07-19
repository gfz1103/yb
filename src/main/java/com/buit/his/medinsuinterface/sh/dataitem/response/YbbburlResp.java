
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：城镇大病上报表<br>
 * 
 * @author beijunhua
 */
@ApiModel(value = "医保报表拆分返回")
public class YbbburlResp extends PageQuery {
	@ApiModelProperty(value = "url")
	private String url;
	@ApiModelProperty(value = "页数")
	private int page;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}