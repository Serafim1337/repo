package lab1;

public class Rabotnik1 implements Runnable {
Garderob garderob;
int cloth;
public boolean isActive=true;
public int counter1=0;
Rabotnik1 (Garderob garderob, int cloth)
{
    this.garderob=garderob;
    this.cloth=cloth;
}


    public void run() {

      for (int i=0;i<cloth;i++){
            garderob.take1(); counter1++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

          System.out.println("..........rab 1 used :"+counter1);
          System.out.println("..........rab1 worktime : "+(counter1*100)+"ms");}




}}
