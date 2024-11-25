package prob04;

import java.util.Arrays;

public class MyStack03<T> {
	private int top;
	private T[] buffer; // runtime 때 실행됨 

	@SuppressWarnings("unchecked")
	public MyStack03(int capacity) {
		/* 구현하기 */
		top = -1;
		buffer = (T[])new Object[capacity];
	}

	public void push(T s) {
		/* 구현하기 */
		if(top == buffer.length - 1) resize();
		buffer[++top] = s;
	}

	public T pop() throws MyStackException {
		/* 구현하기 */
		if(isEmpty()) {
			throw new MyStackException();
		} 
		T result = buffer[top];
		buffer[top--] = null;
		return result;
	}

	public boolean isEmpty() {
		/* 구현하기 */
		return top == -1;
	}

	private void resize() {
		/* 구현하기 */	
		@SuppressWarnings("unchecked")
		T[] newBuffer = (T[])new Object[buffer.length * 2];
		for(int i = 0; i <= top; i++) {
			newBuffer[i] = buffer[i];
		}
		buffer = newBuffer;
	}	
}