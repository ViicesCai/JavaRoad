/**
 * 
 */
package student.entity;

import java.util.List;

/**
 * 分页类
 * 
 * @author CAI
 *
 */
public class Page {
	private int currentPage; // 当前页
	private int pageSize; // 页面大小
	private int totalCount; // 总数据
	private int totalPage; // 总页数
	private List<Student> students; // 数据集合
	
	public Page() {
		super();
	}
	
	public Page(int currentPage, int pageSize, int totalPage, List<Student> students) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.students = students;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		
		// 总页数 = 数据总数 % 页面大小 == 0 ? 数据总数 / 页面大小 : 数据总数 / 页面大小 + 1
		this.totalPage = this.totalCount % this.pageSize == 
				0 ? this.totalCount / this.pageSize : 
					this.totalCount / this.pageSize + 1;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
