package phone.rest.yuyu.mydemoapplication;

/**
 * @author yujialing
 * 2019/1/22
 */
public class JavaDocTest {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2");
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread3");
            }
        });

        try {
            thread3.start();
            thread3.join();

            thread1.start();
            thread1.join();

            thread2.start();
            thread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
