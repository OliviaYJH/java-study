package prob02;

public class Duck extends Bird {

	@Override
	void fly() {
		System.out.println("오리(" + getName() + ")는 날지 않습니다.");
	}

	@Override
	void sing() {
		System.out.println("오리(" + getName() + ")가 소리내어 웁니다.");
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("오리의 이름은 ").append(getName()).append(" 입니다.");
		return sb.toString();
	}
}
