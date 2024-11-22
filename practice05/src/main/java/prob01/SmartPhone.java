package prob01;

public class SmartPhone extends MusicPhone {

	@Override
	public void execute(String function) {
		if(function.equals("앱")) {
			playApp();
		} else if(function.equals("음악")) {
			playMusic();
		} else {
			super.execute(function);
		}
		
	}

	@Override
	protected void playMusic() {
		System.out.println("다운로드해서 음악재생");
	}
	
	void playApp() {
		System.out.println("앱실행");
	}
	
}
