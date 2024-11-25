import java.util.Random;



public class Grid{
    
    private boolean bombGrid[][];
    private int countGrid[][];
    private int numRows;
    private  int numColumns;
    private int numBombs;

// default constructor 
    public Grid(){
        this.numRows=10;
        this.numColumns=10;
        this.numBombs=25;
        this.createBombGrid();
        this.createCountGrid();
    }
    //overload contructor
    public Grid(int rows,int columns){

        this.numRows=rows;
        this.numColumns= columns;


        
        this.numBombs = 25;
        this.createBombGrid();
        this.createCountGrid();

    }
    //overloaded contructor 
   public Grid(int rows,int columns,int  numBombs){

        this.numRows=rows;
        this.numColumns= columns;
        this.numBombs = numBombs;
        this.createBombGrid();
        this.createCountGrid();

    }
    // get the numer of rows
    public int getNumRows(){
        return this.numRows;
    }
    //get the number of bombs
    public int getNumBombs(){
        return this.numBombs;
    }
    // get the number od columns
    public int getNumColumns(){
        return this.numColumns;
    }
    // create a deep copy of the grid
    public boolean[][] getBombGrid(){
        if(this.numRows <=0 || this.numColumns <=0){
            throw new IllegalArgumentException("Grid dimensions must be positive");

        }
if(this.numBombs >this.numRows * this.numColumns){
    throw new IllegalArgumentException("Too many bombs on the grid");
}

        boolean[][]copy = new boolean[this.bombGrid.length][this.bombGrid[0].length];

        for(int i=0;i< this.bombGrid.length;i++){
for(int j=0; j< this.bombGrid.length; j++){
    if(i <this.bombGrid.length && j <this.bombGrid[i].length){
        copy[i][j] =this.bombGrid[i][j];
    }
    
}
        }
return copy;
    }
    //create a deep copy if the grid count
    public int getCountGrid()[][]{
        
        int [][]copy = new int[this.countGrid.length][this.countGrid[0].length];
        for(int i=0;i< this.numRows;i++){
            for(int j=0; j< this.numColumns; j++){
                
                    copy[i][j] =this.countGrid[i][j];
                
                
            }
                    }
        
        return copy;
    }
// check if bomb is at location
    public boolean isBombAtLocation(int row,int columns){
        if(this.bombGrid[row][columns]){
            return true;
            
            
            
                }
                return false;
    }

    //get count at location
    public int  getCountAtLocation(int row, int column){
        int count=0;
        
            count= this.countGrid[row][column];
    
        return count;
    }

//private method to create a bomb
    private  void createBombGrid(){
        if(this.numRows <=0 || this.numColumns <=0){
            throw new IllegalArgumentException("Grid dimensions must be positive");

        }
if(this.numBombs >this.numRows * this.numColumns){
    throw new IllegalArgumentException("Too many bombs on the grid");
}
        bombGrid = new boolean[this.numRows][this.numColumns];

        for(int row =0; row < this.numRows; row++){
            for(int col=0;col <this.numColumns;col++ ){
                bombGrid[row][col] = false;
                
            }
        }
        this.addBombtoGrid();

    }

    //private function to create grid with count
    private void createCountGrid(){

        if(this.numRows <=0 || this.numColumns <=0){
            throw new IllegalArgumentException("Grid dimensions must be positive");

        }
if(this.numBombs >this.numRows * this.numColumns){
    throw new IllegalArgumentException("Too many bombs on the grid");
}
countGrid = new int[this.numRows][this.numColumns];
        for(int i=0;i <this.numRows;i++){
            for (int j=0; j<this.numColumns;j++){
               
                
                countGrid[i][j]= this.getAdjecentValue(i, j);
                

            }
            

        }

        

    }
//to string to print the true and false grid
    public String toString(){

       

        String arrayString ="";
        for(int i =0; i <this.bombGrid.length;i++){
            for(int j= 0; j < this.bombGrid[i].length;j++){
                arrayString+= this.bombGrid[i][j]+ " ";
            }
            arrayString+="\n";
        }
        return arrayString;
    }
    // to string to print the count value of the grid
    public String toGridCount(){
        String arrayString ="";
        for(int i =0; i <this.countGrid.length;i++){
            for(int j= 0; j < this.countGrid[i].length;j++){
                arrayString+= this.countGrid[i][j]+ " ";
            }
            arrayString+="\n";
        }
        return arrayString;

    }
//set the number of bombs and randomly insert them into the grid 
    public  void addBombtoGrid(){
        Random r = new Random();
        int bombsPlaced = 0;// keep track of the bombs places 


        //loop through the 25 bombs
        while(bombsPlaced < this.numBombs){
            
            int randomRow = r.nextInt(this.numRows);
            int randomCol = r.nextInt(this.numColumns);
if(!bombGrid[randomRow][randomCol]){
    bombGrid[randomRow][randomCol] = true;
    bombsPlaced++;


}

           





        }
    }
//helper method to find the  adjacent values and return count
    public int getAdjecentValue(int row,int col){
int count=0;

        int[][]directions ={
            {-1,0},// up
            {1,0},//down
            {0,-1},//left
            {0,1},//right
             {-1,-1},//left up adjacent
             {1,-1},//left down adjacent
             {-1,1},//right up adjacent
             {1,1}//right down adjacent


        };

        if(this.bombGrid[row][col]){
            count++;
        }
        for(int i=0;i< directions.length;i++){
            int [] direction =directions[i];
            int newRow= row + direction[0];
            int newCol = col + direction[1];
if(newRow >=0 && newRow < this.bombGrid.length && newCol >= 0 && newCol< this.bombGrid[0].length ){
    
    if(this.bombGrid[newRow][newCol]){
        count++;
        

    }

}
           
        }


        return count;
    }

    

   
    

}