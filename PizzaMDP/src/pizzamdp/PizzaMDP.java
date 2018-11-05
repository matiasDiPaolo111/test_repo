/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzamdp;


import entidades.TipoPizza;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 *
 * @author PC-MATT
 */
public class PizzaMDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws HibernateException {
        // TODO code application logic here
     
        
          try {
         TipoPizza tipoPizza = new TipoPizza();
         
     
            tipoPizza.update();
        } catch (Exception ex) {
            Logger.getLogger(PizzaMDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
