package task1_timer;

public class Task1Test {
    public static void main(String[] args) {
        System.out.println("---------------Task1 - Timer--------------------");
        Timer timer = new Timer(25);
        Thread thread0 = new Thread(timer);
        Thread thread1 = new Thread(timer);
        thread0.start();
        thread1.start();

        System.gc();
    }

}