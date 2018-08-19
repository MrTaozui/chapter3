package test.threadlocal;

public class SequenceC implements Sequence{
	private MyThreadLocal<Integer> myThreadLocal=new MyThreadLocal<Integer>(){
		@Override
		public Integer initiaValue() {
			return 0;
		};
	};
	@Override
	public int getNumber() {
		int value= myThreadLocal.get();//get里面有初始化
		value++;
		myThreadLocal.set(value);
		return value;
	}
	public static void main(String[] args) {
		SequenceC sequence=new SequenceC();
		ClinetThread thread1=new ClinetThread(sequence);
		ClinetThread thread2=new ClinetThread(sequence);
		ClinetThread thread3=new ClinetThread(sequence);
		thread1.start();
		thread2.start();
		thread3.start();
	}

}
