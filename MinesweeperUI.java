import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MinesweeperUI  extends JFrame{



    public MinesweeperUI(){
        Grid bomb = new Grid(5,5,5);



        setTitle("Minsesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

  

    public static void main(String[] args){
        SwingUtilities.invokeLater(MinesweeperUI::new);
    }

    
}
