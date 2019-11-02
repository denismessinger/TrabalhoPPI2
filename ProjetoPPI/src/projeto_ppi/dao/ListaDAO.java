package projeto_ppi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import projeto_ppi.pojo.Lista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seven
 */
public class ListaDAO {
    private EntityManager em;
    
    public ListaDAO (){
        
    }
    public void salvar (Lista lista){
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
        em.persist(lista);
        em.getTransaction().commit();
        em.close();
    }

    public void excluir (int id) throws Exception{
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
        Lista entity = em.find(Lista.class, id);
        if(entity!=null){
            em.remove(entity);
        }else{
            throw new Exception("Não existe o id: "+ id);
        }
        em.getTransaction().commit();
        em.close();
    }
    public List<Lista> buscar() {
      em = EntityManagerUtil.getEntity();
      TypedQuery<Lista> query = em.createQuery("Select man from Lista man",
                      Lista.class);
      List<Lista> lista = query.getResultList();
      em.close();
      return lista;
    }
    public void atualizar (Lista lista){
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
        em.merge(lista);
        em.getTransaction().commit();
        em.close();
    }
}
