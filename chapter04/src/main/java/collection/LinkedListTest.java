package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListTest {

	public static void main(String[] args) {
		List<String> v = new ArrayList<>();

		v.add("둘리");
		v.add("마이콜");
		v.add("또치");

		// 순회1
		for (int i = 0; i < v.size(); i++) {
			String s = v.get(i);
			System.out.println(s);
		}
		
		// 삭제
		v.remove(2);
		
		// 순회2
		Iterator<String> it = v.iterator();
		while(it.hasNext()) {
			String s = it.next();
			System.out.println(s);
		}
		
		// 순회3
		for(String s: v) {
			System.out.println(s);
		}

	}

}
