package lab1;

public class Rabotnik3 implements Runnable {
Garderob garderob;
public int cloth;
public int counter3;
        Rabotnik3(Garderob garderob, int cloth)
        {
            this.garderob=garderob;
            this.cloth=cloth;
        }

    public void run() {
        for (int i=0;i<cloth;i++){
            garderob.take3(); counter3++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("..........rab3 used : "+counter3);
            System.out.println("..........rab3 worktime : "+(counter3*50)+"ms");}
        }

    }

