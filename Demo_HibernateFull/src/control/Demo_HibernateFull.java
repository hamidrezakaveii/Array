/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Article;
import entity.Client;
import entity.Commande;
import entity.Detaillivraison;
import entity.DetaillivraisonId;
import entity.Lignecommande;
import entity.LignecommandeId;
import entity.Livraison;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author HAMIDREZA
 */
public class Demo_HibernateFull {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        //open the session
        session.beginTransaction();

        //insert
        //Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-21");
        //Livraison livraison = new Livraison(new BigDecimal(106), date);
        Client client = new Client(new BigDecimal(10), "Luc Sansom", "(999)999-9999");

        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2000-06-01");
        Commande commande = new Commande(new BigDecimal(1), client, date2);

        Article article = new Article(new BigDecimal(10), new BigDecimal(5), new BigDecimal(10));

        LignecommandeId lignecommandeId = new LignecommandeId(new BigDecimal(1), new BigDecimal(10));

        Lignecommande lignecommande = new Lignecommande(lignecommandeId, commande, article, new BigDecimal(32));

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-21");
        Livraison livraison = new Livraison(new BigDecimal(107), date);

        DetaillivraisonId detaillivraisonId = new DetaillivraisonId(new BigDecimal(107), new BigDecimal(1), new BigDecimal(10));
        DetaillivraisonId detaillivraisonId2 = new DetaillivraisonId(new BigDecimal(109), new BigDecimal(1), new BigDecimal(10));

        Detaillivraison detaillivraison = new Detaillivraison(detaillivraisonId, livraison, lignecommande, new BigDecimal(3));
        Detaillivraison detaillivraison2 = new Detaillivraison(detaillivraisonId2, livraison, lignecommande, new BigDecimal(10));

        Set livraisonset = new HashSet();

        livraisonset.add(detaillivraison);
        livraisonset.add(detaillivraison2);

        Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-02-22");
        Livraison mylivraison = new Livraison(new BigDecimal(109), date3, livraisonset);

        Detaillivraison detaillivraison3 = new Detaillivraison(detaillivraisonId2, mylivraison, lignecommande, new BigDecimal(11));

        mylivraison.setDetaillivraisons(livraisonset);

        //session.save(livraison);
        //session.save(detaillivraison);
        //session.save(mylivraison);
        //session.update(mylivraison);
        //session.merge(mylivraison);
        session.save(detaillivraison3);
        //livraison.setDetaillivraisons(livraisonset);

        //commit
        session.getTransaction().commit();

        //close session 
        if (session.isConnected()) {
            session.close();
        }
        if (!sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

}
