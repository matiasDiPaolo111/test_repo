/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzamdp;


import entidades.TipoPizza;
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
       // Address address = new Address("OMR Road", "Chennai", "TN", "600097");
        //By using cascade=all option the address need not be saved explicitly when the student object is persisted the address will be automatically saved.
            //session.save(address);
       
      
         
         TipoPizza tipoPizza = (TipoPizza) session.get(TipoPizza.class, 1);
         
         if (tipoPizza != null){
         
         System.out.println(tipoPizza.toString());
         
         }
        
        
        
       
        } catch (HibernateException e) {
        //  System.out.print(e.);
        } finally {
        session.close();
        }
        }
    
}
