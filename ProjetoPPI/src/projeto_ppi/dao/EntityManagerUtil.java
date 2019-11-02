/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_ppi.dao;

import javax.persistence.*;

/**
 *
 * @author Seven
 */
public class EntityManagerUtil {  
    
    private static EntityManagerFactory emf;

    public static EntityManager getEntity() {
        if(emf==null){
            emf =Persistence.createEntityManagerFactory("ProjetoPPIPU");
        }
        return emf.createEntityManager();
    }

    public static void fecharEmf() {
        emf.close();
    } 
}
