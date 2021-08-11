package ladder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class App {
	public static Game[] games = new Game[10];

	public static void main(String[] args) {
		// 写入项目
		Project[] projects = new Project[4];
		Project proj = new Project();
		proj.name = "构筑实训环境";
		proj.desc = "大家需要依次装好以下环境";
		projects[0] = proj;
		proj = new Project();
		proj.name = "思维导图";
		proj.desc = "找出关键类及其属性，行为";
		projects[1] = proj;
		int p_index = 2;
		for(int i = 0 ; i<2 ; i++){
			proj = new Project();
			proj.name = "新增项目"+(i+1);
			proj.desc = "这是一个项目";
			projects[p_index++] = proj;
		}
		// 遍历每个项目
		System.out.print("有如下项目:");
		for (int x = 0; x < projects.length; x++) {
			if (projects[x] != null) {
				System.out.print(projects[x].name+' ');
			}
			if(x == projects.length-1){
				System.out.println();
			}
		}
		// 写入用户
		
		User[] users = new User[7];
		User user = new User();
		user.name = "超级管理员";
		user.loginName = "228228";
		user.passwd = "225225";
		users[0] = user;
		user = new User();
		user.name = "ChaoHe";
		user.loginName = "ChaoHe";
		user.passwd = "ChaoHe225";
		users[1] = user;
		int u_index = 2;
		for(int i = 0 ; i<5 ; i++){
			user = new User();
			user.name = "新增用户"+(i+1);
			user.loginName = "新增用户"+(i+1);
			user.passwd = "123";
			users[u_index++] = user;
		}
		

		// 遍历每个用户
		System.out.print("有如下用户:");
		for (int x = 0; x < users.length; x++) {
			if (users[x] != null) {
				System.out.print(users[x].name+' ');
			}

			if(x == users.length-1){
				System.out.println();
			}
		}
		// 用户登录
		User loginuser = new User();
		loginuser.loginName = "ChaoHe";
		loginuser.passwd = "ChaoHe225";
		
		System.out.format("下面开始进行10场比赛:");


		// 写入loginuser比赛成绩

		Game game = new Game();
		game.user = loginuser;
		game.project = projects[0];
		// loginuser提交项目
		String err = game.submit("loginuser提交项目");
		if (err.length() > 0) {
			System.out.println(err);
		} else {
			// 给loginuser评分
			err = game.score("提交项目评分", 200);
			if (err.length() > 0) {
				System.out.println(err);
			}
		}
		games[0] = game;

		game = new Game();
		game.user = loginuser;
		game.project = projects[1];
		// loginuser提交项目
		err = game.submit("loginuser提交项目2");
		if (err.length() > 0) {
			System.out.println(err);
		} else {
			// 给loginuser评分
			err = game.score("提交项目2评分", 180);
			if (err.length() > 0) {
				System.out.println(err);
			}
		}
		games[1] = game;

		Random random = new Random();
		for(int i =0;i<games.length;i++){
			Game g = new Game();
			g.user = users[random.nextInt(users.length-1)+1];
			g.project = projects[random.nextInt(projects.length)];
			g.score("提交"+g.project.name+"评分",random.nextInt(500));
			games[i] = g;
			games[i].user.point = games[i].point + games[i].user.point;
		}
		// 遍历所有比赛
//		System.out.println(game.user.name + "同学在【" + game.project.name + "】项目中获得" + game.point + "分");
		for (int x = 0; x < games.length; x++) {
			if (games[x] != null) {
				System.out.println(games[x].user.name + "同学在【" + games[x].project.name + "】项目中获得" + games[x].point + "分");
			}
		}

		System.out.format("\33[32;1m比赛结束:-------------------------------------------%n");
		System.out.format("\33[0;0m");

		//rank
		String [] ranks = new String[]{"入门","青铜","白银", "黄金" ,"白金" ,"钻石" ,"大师", "王者"};
		for (int i =0; i<users.length;i++) {
			int index = users[i].point / 300;
			users[i].rank = index > ranks.length-1 ? "王者" : ranks[index];
		}

		for(int i=0;i<users.length;i++){
			Arrays.sort(users,new Comparator<User>(){
				//传入的时候由用户自己选
				@Override
				public int compare(User o1, User o2) {
					// TODO Auto-generated method stub
					return o1.point < o2.point ? o1.point:(o1.point==o2.point) ? 0:-1;

				}
			});
			String hasPlay= users[i].point == 0? "    未参加" : "";
			System.out.println(i+1+". "+users[i].name + "  " + users[i].point+"分    " +users[i].rank +hasPlay);
		}

		System.out.format("统计:-------------------------------------------%n");
		System.out.format("\33[0;0m");

		//全部用户平均分
		int sum_point = 0;
		int count = 10;
		for (User u : users) {
			if(u.point!=0){
				sum_point = sum_point + u.point;
			}
		}
		//某用户平均分
		int num=0;
		double avg=0.0;
		User random_user = users[random.nextInt(users.length-1)+1];
		for (Game g : games) {
			if(g.user.name.equals(random_user.name)){
				num++;
			}
		}
		avg = num==0? 0 :random_user.point/num;
		System.out.println(random_user.name+"平均分为:"+avg);
		System.out.println("全部用户平均分(平均每场比赛):"+sum_point/(double)games.length +"分");
	}


}
