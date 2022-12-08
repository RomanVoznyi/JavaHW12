package task2_print_numbers;

public class PrintNumbers{
    private int current_number = 1;
    private int stop_number;
    private String query = "";
    private final Object monitor = new Object();

    PrintNumbers(int stop_number) {
        this.stop_number = stop_number;
    }

    public void fizz() throws InterruptedException {
        while (current_number <= stop_number) {
            synchronized (monitor) {
                if (current_number % 3 == 0 && current_number % 5 != 0) {
                    query = "fizz";
                    monitor.notifyAll();
                }
                monitor.wait();
            }
        }
    }

    public void buzz() throws InterruptedException {
        while (current_number <= stop_number) {
            synchronized (monitor) {
                if (current_number % 5 == 0 && current_number % 3 != 0) {
                    query = "buzz";
                    monitor.notifyAll();
                }
                monitor.wait();
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        while (current_number <= stop_number) {
            synchronized (monitor) {
                if (current_number % 3 == 0 && current_number % 5 == 0) {
                    query = "fizzbuzz";
                    monitor.notifyAll();
                }
                monitor.wait();
            }
        }
    }

    public void number() throws InterruptedException {
        while (current_number <= stop_number) {
            if (query.equals("") && current_number == 1) {
                System.out.print(current_number);
            } else if (query.equals("") && current_number != 1) {
                System.out.print(", " + current_number);
            } else {
                System.out.print(", " + query);
                query = "";
            }
            current_number++;
            synchronized (monitor) {
                monitor.notifyAll();
            }
            Thread.currentThread().sleep(10);
        }
    }
}
