/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_ppi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import projeto_ppi.pojo.Lista;
import projeto_ppi.pojo.Referencia;
import projeto_ppi.pojo.Usuario;

/**
 *
 * @author Seven
 */
public class ReferenciaDAO {
    private EntityManager em;
    
    public void salvar (Referencia ref){
      em = EntityManagerUtil.getEntity();
      em.getTransaction().begin();
      em.persist(ref);
      em.getTransaction().commit();
      em.close();
    }
    public void atualizar (Referencia ref){
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
        em.merge(ref);
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
    public List<Referencia> buscar() {
      em = EntityManagerUtil.getEntity();
      TypedQuery<Referencia> query = em.createQuery("Select man from Referencia man",
                      Referencia.class);
      List<Referencia> lista = query.getResultList();
      em.close();
      return lista;
    }
}
