锘縫ackage com.lh.model;

/**
 * 鍒嗛〉灏佽绫�
 * @author LH
 */

public class Page {
	private int totalRows = 0; // 鎬昏褰曟暟
	private int totalPages = 0; // 鎬荤殑椤垫暟
	private int pageSize = 10; // 姣忛〉鏄剧ず鐨勮褰曟暟
	private int currentPage = 1; // 褰撳墠椤�
	private boolean hasPrevious = false; // 鏄惁鏈変笂涓�椤�
	private boolean hasNext = false; // 鏄惁鏈変笅涓�椤�
	private int firstResult = 0; //鏁版嵁搴撴煡璇㈢殑寮�濮嬭褰�

	public Page() {
	}

	/**
	 * 鍒濆鍖杙age绫�
	 * 
	 * @param totalRows
	 *            鎬荤殑璁板綍鏁�
	 * @param pageSize
	 *            姣忛〉鏈�澶氭樉绀虹殑淇℃伅鏁�
	 */
	public void init(int totalRows, int pageSize) {
		this.totalRows = totalRows;
		this.pageSize = pageSize;
		totalPages = ((totalRows + pageSize) - 1) / pageSize;
		refresh(); // 鍒锋柊褰撳墠椤甸潰淇℃伅
	}

	/**
	 * 寰楀埌褰撳墠椤�
	 * 
	 * @return 褰撳墠椤�
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 璁剧疆褰撳墠椤�
	 * 
	 * @param currentPage
	 *            褰撳墠椤�
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		refresh();
	}

	/**
	 * 寰楀埌姣忛〉鏄剧ず鐨勮褰曟暟
	 * 
	 * @return
	 * 
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 璁剧疆姣忛〉鏄剧ず鐨勮褰曟暟
	 * 
	 * @param pageSize
	 *            姣忛〉鏄剧ず鐨勮褰曟暟
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		refresh();
	}

	/**
	 * 寰楀埌鎬荤殑椤垫暟
	 * 
	 * @return Returns 鎬荤殑椤垫暟
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * 璁剧疆鎬荤殑椤垫暟
	 * 
	 * @param totalPages
	 *            鎬婚〉鏁�
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
		refresh();
	}

	/**
	 * 寰楀埌鎬荤殑璁板綍鏁�
	 * 
	 * @return 鎬荤殑璁板綍鏁�
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * 璁剧疆鎬荤殑璁板綍
	 * 
	 * @param totalRows
	 *            鎬荤殑璁板綍鏁�
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		refresh();
	}

	/**
	 * 悃堟妸鎶婇〉闈€悃堝畫 悃堟妸鎶婃妸璁剧疆 璁剧疆椤甸潰涓洪椤碉紝骞舵妸鏄惁鏈変笂涓�椤佃涓篺alse,鍒锋柊褰撳墠椤甸潰
	 */

	public void first() {
		currentPage = 1;
		this.setHasPrevious(false);
		refresh();
	}

	/**
	 * 涓婁竴椤碉紝骞跺埛鏂板綋鍓嶉〉闈�
	 */
	public void previous() {
		currentPage--;
		refresh();
	}

	/**
	 * 涓嬩竴椤碉紝骞跺埛鏂板綋鍓嶉〉闈�
	 */
	public void next() {

		if (currentPage < totalPages) {
			currentPage++;
		}
		refresh();
	}

	/**
	 * 灏鹃〉骞跺埛鏂板綋鍓嶉〉闈�
	 */
	public void last() {
		currentPage = totalPages;
		this.setHasNext(false);
		refresh();
	}

	/**
	 * 鏄惁鏈変笅涓�椤�
	 * 
	 * @return 鏄惁鏈変笅涓�椤�
	 */
	public boolean isHasNext() {
		return hasNext;
	}

	/**
	 * 璁剧疆涓嬩竴椤�
	 * 
	 * @param hasNext
	 *            涓嬩竴椤�
	 */
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	/**
	 * 鏄惁鏈変笂涓�椤�
	 * 
	 * @return 鏄惁鏈変笂涓�椤�
	 */

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	/**
	 * 璁剧疆鏄惁鏈変笂涓�椤�
	 * 
	 * @param hasPrevious
	 *            涓婁竴椤�
	 */
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	/**
	 * 鍒锋柊褰撳墠椤甸潰
	 */
	public void refresh() {
		if (totalPages <= 1) {
			hasPrevious = false;
			hasNext = false;
		} else if (currentPage == 1) {
			hasPrevious = false;
			hasNext = true;
		} else if (currentPage == totalPages) {
			hasPrevious = true;
			hasNext = false;
		} else {
			hasPrevious = true;
			hasNext = true;
		}
	}

	/**
	 * 寰楀埌璇ラ〉闈㈢殑绗竴鏉¤褰�
	 * 
	 * @return 寰楀埌鏄剧ず椤甸潰鐨勭涓�鏉¤褰�
	 */

	public int getFirstResult() {
		return firstResult;
	}

	/**
	 * 璁剧疆椤甸潰鐨勭涓�鏉¤褰�
	 * 
	 * @param 绗竴鏉¤褰曠殑浣嶇疆
	 */
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

}
