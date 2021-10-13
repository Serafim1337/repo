package lab1;

public class Rabotnik2 implements Runnable {
    Garderob garderob;
    int cloth;
    public int counter2=0;
    Rabotnik2 (Garderob garderob, int cloth)
    {
        this.garderob=garderob;
        this.cloth=cloth;
    }
    public void run() {
        for (int i=0;i<cloth;i++){
            garderob.take2(); counter2++;
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("..........rab2 used : "+counter2);
            System.out.println("..........rab2 worktime : "+(counter2*80)+"ms");}
        }
    }

