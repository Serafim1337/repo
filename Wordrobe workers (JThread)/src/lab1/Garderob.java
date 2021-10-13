package lab1;

public class Garderob
{

    public int cloth=45;
    public synchronized void take1 ()
    {

        while (cloth==0){
            try {
                wait();
            }
            catch (InterruptedException e){}
        }
        cloth--;
      // System.out.println("--------------");
        System.out.println("rab1 take"); System.out.println("ostalos>"+cloth);
       // System.out.println("--------------");
        notify();
        if (cloth==0)
        {
            System.out.println("--------------");
            System.out.println("cloth end<<");
            System.out.println("--------------");
        }

    }
    public synchronized void take2()
    {
        while (cloth==0){
            try {
                wait();
            }
            catch (InterruptedException e){}
        }
        cloth--;
        System.out.println("rab2 take"); System.out.println("ostalos>"+cloth);
        notify();
        if (cloth==0)
        {
            System.out.println("--------------");
            System.out.println("cloth end<<");
            System.out.println("--------------");
        }
    }
    public synchronized void take3()
    {
        while (cloth==0){
            try {
                wait();
            }
            catch (InterruptedException e){}
        }
        cloth--;
        System.out.println("rab3 take"); System.out.println("ostalos>"+cloth);
        notify();
        if (cloth==0)
        {

            System.out.println("--------------");
            System.out.println("cloth end<<");
            System.out.println("--------------");
        }
    }

}
