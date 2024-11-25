package prob04;

import java.util.Arrays;

public class MyStack02 {
	private int top;
	private Object[] buffer;

	public MyStack02(int capacity) {
		/* 구현하기 */
		top = -1;
		buffer = new String[capacity];
	}

	public void push(Object s) {
		/* 구현하기 */
		if(top == buffer.length - 1) resize();
		buffer[++top] = s;
	}

	public Object pop() throws MyStackException {
		/* 구현하기 */
		if(isEmpty()) {
			throw new MyStackException();
		} 
		Object result = buffer[top];
		buffer[top--] = null;
		return result;
	}

	public boolean isEmpty() {
		/* 구현하기 */
		return top == -1;
	}

	private void resize() {
		/* 구현하기 */	
		Object[] newBuffer = new Object[buffer.length * 2];
		for(int i = 0; i <= top; i++) {
			newBuffer[i] = buffer[i];
		}
		buffer = newBuffer;
	}	
}