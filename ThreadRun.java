

class ThreadRun implements Runnable { 
   
   private Warehouse warehouse; //Used as an instance of warehouse
   private int type; //Type 1 for add, else for subtract
   private boolean useBug; //Whether to use bug flag or not
   private Thread t; //thread

   
   
   /**
    * 
    * @param warehouse instance of warehouse
    * @param typeA type; 1 add, 0 subtract
    * @param useBugA //use bug flag or not
    */
   public ThreadRun(Warehouse warehouse,int typeA,boolean useBugA){
      this.warehouse = warehouse;
      this.useBug=useBugA;
      this.type=typeA;
      //this.t=t;
      //Constructor method
   }

   public ThreadRun(Warehouse warehouse,int typeA,boolean useBugA,Thread t){
      this.warehouse = warehouse;
      this.useBug=useBugA;
      this.type=typeA;
      this.t=t;
      //Constructor method
   }
   
   public void run() {

   

      if(this.type==1){ //adding operation

         if(this.useBug){

          

               try{
                  System.out.println("Adding 1, now: " + Integer.toString(warehouse.getSum()) );
                  t.join();//lock the thread
                  
                  add();

               }
               
               catch(InterruptedException ex){
                  ex.printStackTrace();
               }
               catch(NullPointerException ex){
                  
               }


            
            
         }
          
         else{ //non bug flag
            add();
         }
         
      }

      else{ //subtract operation
         if(this.useBug){
            
            try{
               //System.out.println("Removing 1, now: " + Integer.toString(warehouse.getSum()) );
               t.join();//join thread, lets other threads make changes
               subtract();
            }
            
            catch(InterruptedException ex){
               ex.printStackTrace();
            }
            catch(NullPointerException ex){
                //ignore if t.null is true
            }
               
            
            
         }
         
         else{
            subtract();
         }
         

      }

   }


   public synchronized  void add(){
      //fetches and adds on one then sets it back
     warehouse.setSum(1);
    // System.out.println("Adding 1, now: "+ Integer.toString(warehouse.getSum()) );
      
   }



   public synchronized void subtract(){

      warehouse.setSum(0);
     // System.out.println("Removing 1, now: "+ Integer.toString(warehouse.getSum()) );
      
    }

   
   
}


