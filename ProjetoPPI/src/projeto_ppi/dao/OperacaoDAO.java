/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_ppi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import projeto_ppi.pojo.Manuais;
import projeto_ppi.pojo.Operacao;

/**
 *
 * @author Seven
 */
public class OperacaoDAO {
    private EntityManager em;
    
    public OperacaoDAO (){
        
    }
    public void getEntityManager(){
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
    }
    
    public List<Operacao> buscar() {
      em = EntityManagerUtil.getEntity();
      TypedQuery<Operacao> query = em.createQuery("Select man from Operacao man",
                      Operacao.class);
      List<Operacao> lista = query.getResultList();
      em.close();
      return lista;
    }
    public void excluir (int id) throws Exception{
        getEntityManager();
        Operacao entity = em.find(Operacao.class, id);
        if(entity!=null){
            em.remove(entity);
        }else{
            throw new Exception("Não existe o id: "+ id);
        }
        em.getTransaction().commit();
        em.close();
    }
    public void salvar (Operacao op){
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
        em.persist(op);
        em.getTransaction().commit();
        em.close();
    }
}
