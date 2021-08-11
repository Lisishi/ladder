package ladder;

public class Game {
	public Project project;
	public User user;
	public int point;
	public String desc;
	public String comment;

	
	public String submit(String desc) {
		if (desc.length() < 5) {
			return "您输入的项目描述信息太短";
		} else {
			this.desc = desc;
			return "";
		}
	}
	
	public String score(String comment, int point) {
		if (comment.length() < 5) {
			return "您输入的项目评论信息太短";
		} else if (point < 0 || point > 200) {
			return "您输入的评分必须在0-200分之间";
		} else {
			this.comment = comment;
			this.point = point;
			return "";
		}
	}
}