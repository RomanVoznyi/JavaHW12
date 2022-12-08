package task2_print_numbers;

public class Task2Test {
    public static void main(String[] args) {
        System.out.println("---------------Task2 - Print 'Numbers' Line--------------------");
        PrintNumbers printNumber = new PrintNumbers(25);

        Thread threadA = new Thread(() -> {
            try {
                printNumber.fizz();
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                printNumber.buzz();
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                printNumber.fizzbuzz();
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        });
        Thread threadD = new Thread(() -> {
            try {
                printNumber.number();
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        System.gc();
    }
}