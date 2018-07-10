package Beans;

import java.util.List;

public class Page<T> {

	//Ĭ��ÿҳ��¼��
	private static final int DEFAULT_PAGE_SIZE = 20;
	
	// ҳ��
	private int pageNo;

	// ÿҳ��¼��
	private int pageSize;

	// ��ҳ��
	private int totalPage;

	// ��һҳ
	private int prePage;

	// ��һҳ
	private int nextPage;

	// �Ƿ�����һҳ
	private boolean hasPrePage;

	// �Ƿ�����һҳ
	private boolean hasNextPage;

	// �ܼ�¼��
	private int totalCount;

	// ÿҳ�ļ�¼����
	private List<T> pageContent;

	// ������ҳ��
	private int calTotalPage() {
		int totalPage = 0;
		if (totalCount > 0 && pageSize > 0) {
			totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
		}
		return totalPage;
	}

	public Page(int pageNo, int pageSize, int totalCount, List<T> pageContent) {
		super();
		this.pageNo = (pageNo > 0) ? pageNo : 1;
		this.pageSize = (pageSize > 0) ? pageSize : DEFAULT_PAGE_SIZE;
		this.totalCount = (totalCount > 0) ? totalCount : 0;
		this.totalPage = this.calTotalPage();
		this.pageContent = pageContent;
		this.hasPrePage = (this.pageNo - 1 >= 1);
		this.hasNextPage = (this.pageNo + 1 <= getTotalPage());
		this.prePage = this.hasPrePage ? (this.pageNo - 1) : 1; // ��ȡ��һҳ��ҳ��
		this.nextPage = this.hasNextPage ? (this.pageNo + 1) : totalPage; // ��ȡ��һҳ��ҳ��
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getPageContent() {
		return pageContent;
	}

	public void setPageContent(List<T> pageContent) {
		this.pageContent = pageContent;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

}
