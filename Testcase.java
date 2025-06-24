public class Testcase {

    public static void main(String[] args){


        Grid bomb = new Grid(5,5,1230);
       bomb.getBombGrid();

        System.out.println(bomb.toString());
        System.out.println(bomb.toGridCount());

    }
    
}
