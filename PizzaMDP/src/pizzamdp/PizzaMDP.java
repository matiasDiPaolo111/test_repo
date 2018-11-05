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
     
        
        SessionFactory sessionFactory =
        new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
       
        try {
       
            
            session.beginTransaction();
    
         
         TipoPizza tipoPizza = (TipoPizza) session.get(TipoPizza.class, 1);
         
           if (tipoPizza != null){
         
         System.out.println(tipoPizza.getDescripcionPizza());
         System.out.println(tipoPizza.getId_tipo_pizza());
         System.out.println(tipoPizza.getNombre());
         
         }else{
           System.out.println("No existe el elemento ");
           }
        
        
        
       
        } catch (Exception e) {
          System.out.print(e.toString());
        } finally {
        session.close();
        }
        }
    
}