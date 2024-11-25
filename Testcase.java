public class Testcase {

    public static void main(String[] args){


        Grid bomb = new Grid(16,15,20);
       bomb.getBombGrid();

        System.out.println(bomb.toString());
        System.out.println(bomb.toGridCount());

    }
    
}
