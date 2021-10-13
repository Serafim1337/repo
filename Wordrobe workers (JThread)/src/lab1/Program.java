package lab1;
public class Program {
        public static void main(String[] args) {

                Garderob garderob = new Garderob();
                Rabotnik1 rabotnik1 =new Rabotnik1 (garderob, garderob.cloth);
                Rabotnik2 rabotnik2 =new Rabotnik2 (garderob,garderob.cloth);
                Rabotnik3 rabotnik3 = new Rabotnik3(garderob,garderob.cloth);
                new Thread(rabotnik1).start();
                new Thread(rabotnik2).start();
                new Thread(rabotnik3).start();
        }
}
