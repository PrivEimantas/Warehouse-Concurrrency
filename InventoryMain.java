
/**
 * main class
 */
public class InventoryMain{

    /**
     *  Main Method
     * @param args main
     */
    public static void main(String args[]) {
      Warehouse warehouse = new Warehouse(); //create instance of warehouse items
      
      int addOps=0; //Initialise parameters so can be set in terminal
      int removeOps=0;
      int bugFlag=0;
      


      for(String s: args){ // Loop to fetch values from user in terminal
        
        if(args.length > 0){ 
          try{
            addOps = Integer.parseInt(args[0]);
            removeOps = Integer.parseInt(args[1]);
            bugFlag = Integer.parseInt(args[2]);
          }
          catch(NumberFormatException e){
            System.err.println("Argument " + args[0] + " must be an integer");
            System.err.println("Argument " + args[1] + " must be an integer");
            System.err.println("Argument " + args[2] + " must be an integer");
            System.exit(1);
          }
        }
      }

      
      

      for(int i=0;i<addOps;i++){ // add operations
        if(bugFlag==0){
          Thread t = new Thread(new ThreadRun(warehouse,1,false));
          t.start();
        }
        else{
          Thread t = new Thread(new ThreadRun(warehouse,1,true));
          t.start();
        }
        
      }
     

      for(int i=0;i<removeOps;i++){ // remove operations
        if(bugFlag==0){
          Thread t = new Thread(new ThreadRun(warehouse,0,false));
          t.start();
        }
        else{ //Bug flag remove thread
          if(addOps==0){
            Thread t = new Thread(new ThreadRun(warehouse,0,true, new Thread(new ThreadRun(warehouse,1,true)))  );
            t.start();
          }
          
          Thread t = new Thread(new ThreadRun(warehouse,0,true, new Thread(new ThreadRun(warehouse,0,true)))  );
          t.start();


          
         
        }
        
      }
    
      
      try{ //Wait for last thread to finish to print inventory size

        Thread.sleep(700);
        System.out.println("Final inventory size: "+warehouse.getSum());
        //System.exit(0);
      }
      catch(InterruptedException ex){
        ex.printStackTrace();
      }
      


      
    
     




      
      
    }

}