package test.threadlocal;

/**
 * @author tjj .
 */
public class ClinetThread extends Thread {
    private Sequence sequence;

    public ClinetThread(Sequence sequence){
        this.sequence=sequence;
    }

    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.println(Thread.currentThread().getName()+ " => "+sequence.getNumber());
        }
    }
}
