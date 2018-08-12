/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_hibernate3;

import entite.Client;
import entite.Commande;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author 1795545
 */
public class AppCtr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        //open the session
        session.beginTransaction();

        //set date
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-16");

        //insert
        Client myClient = new Client(new BigDecimal(7007), "James Bond", "514007007");
        Commande myCommande = new Commande(new BigDecimal(100100100), myClient, date);

        myCommande.setNocommande(new BigDecimal(700700700));

        //save the object
        session.save(myClient);
        session.save(myCommande);

        //update the object
        session.update(myCommande);

        //delete the object
        session.delete(myCommande);
        session.delete(myClient);

        //commit
        session.getTransaction().commit();

        //select
        Query query = session.createQuery("from Commande");

        List<Commande> resultat = query.list();

        for (Commande line : resultat) {
            System.out.println("NO commande: " + line.getNocommande() + " Date commande: "
                    + line.getDatecommande() + " No client: " + line.getClient().getNomclient());
        }
        System.out.println("**************************************************************");
        //select 2
        Query query2 = session.createQuery("Select c.noclient, c.nomclient, count(noCommande)"
                + " from Client c, Commande m where c.noclient = m.client.noclient group by c.noclient, c.nomclient");

        List<Object[]> results = query2.list();

        for (Object[] line : results) {
            System.out.println("No client: " + line[0] + " Nom client: " + line[1] + " Nb commande " + line[2]);
        }

        if (session.isConnected()) {
            session.close();
        }
        if (!sessionFactory.isClosed()) {
            sessionFactory.close();
        }

    }

}
