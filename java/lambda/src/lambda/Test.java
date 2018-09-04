package lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	
	static class User{
		private int id;
		private String name;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		for(int i = 0; i < 10; i ++) {
			map.put("" + i, "" + i);
		}
        
        map.forEach((k, v) -> {
            v = v + 10;
            map.put(k, v);//更新。
        });
        
        map.forEach((k, v) -> System.out.println("k " + k + " v " + v));
        
        System.out.println("");
        System.out.println("----------------------------------------------------------");
        System.out.println("");
        
        List<User> list = new ArrayList<User>();
        for(int i = 0; i < 10; i ++) {
        	User user = new User();
        	user.setId(i);
        	user.setName("刘德华" + i);
			list.add(user);
		}
        
        list.forEach(user -> {
        	user.setName("周润发" + user.getId());
        });
        
        list.forEach(System.out::println);
	}

}
