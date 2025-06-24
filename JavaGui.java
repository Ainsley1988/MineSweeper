import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaGui {
private static int count=0;//keep track of score
    public static void main(String[] args){
        JFrame frame = new JFrame("The Bomb app");// create the JFrame
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,700);// set the side of the frame
        Grid bomb = new Grid(5,5,5);// grid constructor 
        GridLayout gridme = new GridLayout(bomb.getNumRows(),bomb.getNumColumns());// get set the number of rows and cols in the Jframe
        gridme.setHgap(0);//remove horizontal gap
        gridme.setVgap(0);//removes  vertical gaps
         System.out.println(bomb.toString());
        int maxscore= (bomb.getNumColumns() * bomb.getNumRows()) - bomb.getNumBombs();//get the max score possible
        
        frame.setLayout(gridme); // Create a grid with layout
    Color[]colors={ new Color(144,238,144), new Color(34,139,34)};//arrays of color for the buttons and for the background
    JButton[][] buttons = new JButton[bomb.getNumRows()][bomb.getNumColumns()];//array of buttons
    
for(int i=0;i<bomb.getBombGrid().length;i++){ //loop through the bomb grid to add buttons to the Jrame
    for(int j =0; j<bomb.getBombGrid()[i].length;j++){

        final int row=i;//assign the row and col to final int
        final int col= j;
        JButton button = new JButton("");//create the empty Jbutton
        Color bg = colors[(i+j) % colors.length];
        int brightness =(int) Math.sqrt(
            bg.getRed() * bg.getRed() * 0.241 +
    bg.getGreen() * bg.getGreen() * 0.691 +
    bg.getBlue() * bg.getBlue() * 0.068
        );
        
            button.setBackground(bg);//loop through the colors in the array
            button.setOpaque(true);
       
            button.setMargin(new Insets(0,0,0,0));
            button.setBorderPainted(false);
            button.setForeground(brightness < 130 ? Color.WHITE : Color.BLUE);
            button.setFont(button.getFont().deriveFont(Font.BOLD,29f));
            buttons[row][col] = button;
           
            
button.addActionListener(new ActionListener() {//button listner  
@Override
public void actionPerformed(ActionEvent e){
boolean bombFound= SendBomb(button,bomb,bomb.getBombGrid()[row][col],row,col);// check if bomb  has been found
 
 button.setEnabled(false);//disable  that specific button  if bomb is found
 System.out.println("Score:"+ count);
    if(bombFound){
        // if found update the button text and end the game
        button.setText("💣");//change the text box
        
        endGame(count, buttons,frame,"You hit a Bomb",bomb);// end the game
        return;

    } else{
     int bombNumber= bomb.getCountAtLocation(row, col);// if  no bomb is found get he adjcent values of the bomb in the location
        button.setText(Integer.toString(bombNumber));// update the button text the the adjacent value
       
        
        count++;
       if(bombNumber == 0){// check if bombs is equal to 0
        button.setBackground(new Color(229,194,144));//set the button color 
        button.setOpaque(true);
        
        adjacentCells(buttons,row,col,bomb);// shows the adjacent cells of the  when the count is 0
        

       }
        System.out.println("the current scroere "+ count);
    }
    if(count == maxscore){
        System.out.println("the game is won");
        endGame(count, buttons,frame,"You won Yeah",bomb);// end the game
        return;
    }
   
   
}
    
});

   
       //add buttons to the frame 
        frame.add(button);

    }
}

        frame.setVisible(true);


    }
    
public static void adjacentCells(JButton button[][],int row,int col,Grid bomb){
    boolean[][]processed;//track grid
    processed = new boolean[button.length][button[0].length];// initialized the process grid 
    int[][]directions ={
        {-1,0},// up
        {1,0},//down
        {0,-1},//left
        {0,1},//right
         {-1,-1},//left up adjacent
         {1,-1},//left down adjacent
         {-1,1},//right up adjacent
         {1,1},//right down adjacent


    };
    Color[]colors={ new Color(229,194,144), new Color(215,184,153)};
    for(int i=0;i< directions.length;i++){
        int [] direction =directions[i];
        int newRow= row + direction[0];
        int newCol = col + direction[1];
        if(newRow >=0 && newRow < bomb.getBombGrid().length && newCol >= 0 && newCol< bomb.getBombGrid()[0].length ){

            if(!processed[newRow][newCol]){// insure that the value are updated twice and that the count is correct 
                int bombCount = bomb.getCountAtLocation(newRow, newCol);
            button[newRow][newCol].setText(Integer.toString(bombCount));
            button[newRow][newCol].setBackground(colors[(newRow + newCol) % colors.length]);
            button[newRow][newCol].setEnabled(false);
            button[newRow][newCol].setOpaque(true) ;
            
        
            count++;

            processed[newRow][newCol]= true;


            }
            

        }
       

    
    }
}
//keep tracks of the score and increment it each time 
//     private static int getScore(){

        

// count++;

//         return count;

//     }

    public static void endGame(int count,JButton[][] button,JFrame p,String status,Grid bomb){
// loop through the buttons and  and disables them once the bomb has been found
for(int i =0; i <button.length;i++){
    for(int j =0;j <button[i].length;j++){
        String name = "";
        if(bomb.getBombGrid()[i][j]){
        name ="💣";

        }else{

            name=String.valueOf(bomb.getCountGrid()[i][j]);

        }
        button[i][j].setText(name);
        button[i][j].setEnabled(false);

    }

}


JDialog endPanel = new JDialog(p,"Game over",true);
endPanel.setSize(300,150);
endPanel.setLayout(new BorderLayout());

JLabel message= new JLabel(status);
JPanel buttonPanel = new JPanel();
JButton restart= new JButton("Restart");
JButton exit = new JButton("Exit");
restart.addActionListener(e->{
    endPanel.dispose();
    p.dispose();
    main(new String[]{});

});
exit.addActionListener(e ->{
    endPanel.dispose();
    p.dispose();
});


buttonPanel.add(restart);
buttonPanel.add(exit);
endPanel.add(message,BorderLayout.CENTER);
endPanel.add(buttonPanel,BorderLayout.SOUTH);
endPanel.setVisible(true);



    }

    public static boolean SendBomb(JButton button,Grid bomb,boolean bombGrid,int i,int j){

    boolean bomber=false;
 
        
        if(bombGrid){


            bomber=true;
            // if bomb is detected end the game 
        
           
            JFrame newgame = new JFrame("Bomb");
            newgame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newgame.setSize(300,200);
            count++;
           
            
        }else{

           bomber=false;
           

        }
        
        return bomber;

    }
    
}
