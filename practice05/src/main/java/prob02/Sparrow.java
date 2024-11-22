package prob02;

public class Sparrow extends Bird {

	@Override
	void fly() {
		System.out.println("참새(" + getName() + ")가 날아다닙니다.");
	}

	@Override
	void sing() {
		System.out.println("참새(" + getName() + ")가 소리내어 웁니다.");
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("참새의 이름은 ").append(getName()).append(" 입니다.");
		return sb.toString();
	}
}
