package task1_timer;

public class Timer implements Runnable {

    private int current_time = 0;
    private int stop_time = 20;
    private boolean regular_timer = false;
    private final Object monitor = new Object();

    public Timer(int stop_time) {
        if (stop_time > 0) {
            this.stop_time = stop_time;
        }
    }

    private void runRegularTimer() throws InterruptedException {
        Thread timer = Thread.currentThread();
        System.out.println("Regular timer '" + timer.getName() + "' started");

        while (current_time <= stop_time) {
            System.out.println(current_time);
            timer.sleep(1000);
            current_time++;
            synchronized (monitor) {
                monitor.notifyAll();
            }
        }
        System.out.println("Timer " + timer.getName() + " stopped");
    }

    private void runPeriodicTimer() throws InterruptedException {
        Thread timer = Thread.currentThread();
        System.out.println("Periodic timer '" + timer.getName() + "' started");

        while (current_time <= stop_time) {
            if (current_time != 0 && current_time % 5 == 0) {
                System.out.println("5 seconds have passed");
            }
            synchronized (monitor) {
                monitor.wait();
            }
        }
        System.out.println("Timer " + timer.getName() + " stopped");
    }

    @Override
    public void run() {
        synchronized (this) {
            regular_timer = !regular_timer;
        }
        try {
            if (regular_timer) {
                runRegularTimer();
            } else {
                runPeriodicTimer();
            }
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }

    }
}
