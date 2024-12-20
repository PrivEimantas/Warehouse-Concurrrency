/**
 * warehouse class for inventory
 */
public class Warehouse{

    private int inventorySize;

    /**
     * constructor method
     */
    public Warehouse(){
        
        this.inventorySize=0;
    }

    /**
     * 
     * @return returns current inventory size
     */
    public synchronized int getSum(){ 
        return(this.inventorySize);

    }

    /**
     * 
     * 
     * @param type Whether to add or not, 1 for add 0 for subtract
     */
    public synchronized void setSum(int type){ //sets inventory size, need this to keep it private
        int curSum = getSum(); 
        //initially set to two seperate methods, but this may cause issues, all done in one method instead one
        if(type==1){

            this.inventorySize=curSum+1;
            System.out.println("Adding 1, now: "+ Integer.toString(getSum()) );
        }
        else{
            this.inventorySize=curSum-1;
            System.out.println("Removing 1, now: "+ Integer.toString(getSum()) );
        }

    }

}