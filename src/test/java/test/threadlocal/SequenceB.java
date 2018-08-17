package test.threadlocal;

/**
 * @author tjj .
 */
public class SequenceB implements Sequence {
    private static ThreadLocal<Integer> numberContainer=new ThreadLocal<Integer>(){
        @Override
        protected  Integer initialValue(){
            return 0;
        }
    };

    @Override
    public int getNumber() {
       numberContainer.set(numberContainer.get()+1);
       return numberContainer.get();
    }

    public static void main(String[] args) {
        Sequence sequence=new SequenceB();
        ClinetThread thread1=new ClinetThread(sequence);
        ClinetThread thread2=new ClinetThread(sequence);
        ClinetThread thread3=new ClinetThread(sequence);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
