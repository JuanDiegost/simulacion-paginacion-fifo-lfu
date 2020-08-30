/*
 * Author: Kimler Corey
 * 
 */
package simulator;

/**
 *
 * @author kimler (kc) Corey
 */



public class Simulator {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        //init area

    // Selected menu item
    int sel; 
    //new menu and user input
    Menu menu = new Menu();
    
    do {
    sel = menu.doMenu();
    
    switch(sel)
    {    
        case 0: // 0 – Exit
            System.out.println("Adiosito !!");
        break;
            
        case 1: // 1 – Read reference string
            menu.readRS();
        break;

        case 2: // 4 – Simulate FIFO
            if (menu.selectedReferenceStringBuffer==null) {menu.block(2); break;}
            Pager fifo = new Pager (menu.selectedReferenceStringBuffer,0);
            fifo.run();
        break;    

        case 3: // 6 – Simulate LFU
            if (menu.selectedReferenceStringBuffer==null) {menu.block(2); break;}
            Pager lfu = new Pager (menu.selectedReferenceStringBuffer,2);
            lfu.run();
        break;
        
        default: System.out.println("Opción no valida");
    }
    System.out.println("-----------------------------------------------");
    } //end do 
    while (sel != 0);
    
    //String menu = "menu:\n";
    
    
    }  
   

    
}
