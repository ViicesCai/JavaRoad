/**
 * 
 */
package java6601.task;

/**
 * @author CAI
 *
 */
public class Course6601 implements Comparable<Course6601>{
	private String id; // 课程id
	private String name; // 课程名
	private String term; // 学期
	private int credit; // 学分
	private int score; // 分数
	
	public Course6601() {
		super();
	}
	
	public Course6601(String id, String name, String term, int credit, int score) {
		super();
		this.id = id;
		this.name = name;
		this.term = term;
		this.credit = credit;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return String.format("%s %-12s\t%s,%d,%d", id, name, term, credit, score);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credit;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course6601 other = (Course6601) obj;
		if (credit != other.credit)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		return true;
	}

	@Override
	public int compareTo(Course6601 course) {
		// 检查对象是否重复
		if (equals(course)) {
			return 0;
		}
		
		// 如果当前的成绩 > course对象的成绩 则返回-1，否则返回1
		// 当前对象 > 后一个对象，返回结果值为-1时，说明是降序排列
		return this.score > course.score ? -1:1;
	}
}
