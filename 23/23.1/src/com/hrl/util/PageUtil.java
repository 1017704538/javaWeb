package com.hrl.util;

public class PageUtil {
	private Integer pageSize = 10;// 涓�椤垫樉绀烘潯鏁�
	private Integer recordCount = 0;// 鎬绘潯鏁�
	private Integer index = 0;// 绱㈠紩涓嬫爣
	private Integer currPage = 1;// 褰撳墠椤垫暟

	/**
	 * 鑾峰彇鎬荤殑椤垫暟
	 * 
	 * @return
	 */
	public Integer getPageCount() {
		Integer size = this.recordCount / this.pageSize;
		Integer mod = recordCount % pageSize;
		if (mod != 0) {
			size++;
		}
		return this.recordCount == 0 ? 1 : size;
	}

	/**
	 * 鏄惁鏈変笂涓�椤�
	 * 
	 * @return
	 */
	public boolean isHasPrevious() {
		//涓嶆槸绗竴椤�
		if (this.currPage != 1) {
			return true;
		}
		return false;
	}

	/**
	 * 鏄惁鏈変笅涓�椤�
	 * 
	 * @return
	 */
	public boolean isHasNext() {
		//涓嶆槸鏈�鍚庝竴椤�
		if (this.currPage != this.getPageCount()) {
			return true;
		}
		return false;
	}

	public Integer getNextIndex() {
		return this.currPage * this.pageSize;
	}

	public Integer getPreviousIndex() {
		return (this.currPage - 2) * this.pageSize;
	}

	/**
	 * 寰楀埌绱㈠紩
	 * 
	 * @return
	 */
	public Integer getIndex() {
		return this.index;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * 寰楀埌褰撳墠椤�
	 * 
	 * @return
	 */
	public Integer getCurrPage() {
		Integer c = index / pageSize;
		if ((index + 1) % pageSize != 0) {
			c++;
		}
		return c == 0 ? 1 : c;
	}

	/**
	 * 寰楀埌鏈〉鐨勭储寮�
	 * 
	 * @return
	 */
	public Integer getLastIndex() {
		return this.getPageCount()*this.pageSize;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

}
