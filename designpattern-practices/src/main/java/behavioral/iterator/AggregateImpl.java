package behavioral.iterator;

public class AggregateImpl<E> implements Aggregate<E> {
	private E[] datas = null;
	
	public AggregateImpl(E[] datas) { // 생성자 
		this.datas = datas; 
	}

	@Override
	public Iterator<E> createIterator() {
		return new IteratorImpl();
	}
	
	private class IteratorImpl implements Iterator<E> { // inner class의 장점: outer class의 private 변수에 접근 가능하다!
		private int index = 0;

		@Override
		public E next() {
			return index < datas.length ? datas[index++] : null;
		}

		@Override
		public boolean hasNext() {
			return index < datas.length;
		}
		
	}
}
