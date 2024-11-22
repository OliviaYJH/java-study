package prob05;

public class MyBase extends Base {

	@Override
	public void service(String state) {
		switch(state) {
			case "낮": 
				day();
				break;
			case "밤":
				night();
				break;
			case "오후":
				afternoon();
				break;
		}
	}

	@Override
	public void day() {
		System.out.println("낮에는 열심히 일하자!");
	}

	
	public void afternoon() {
		System.out.println("오후도 낮과 마찬가지로 일해야 합니다.");
	}
	
}
