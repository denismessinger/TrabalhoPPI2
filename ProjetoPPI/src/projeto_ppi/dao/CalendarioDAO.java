/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_ppi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import projeto_ppi.pojo.Calendario;

/**
 *
 * @author Seven
 */
public class CalendarioDAO {
    
    private EntityManager em;
    
    public CalendarioDAO (){
        
    }
    public void getEntityManager(){
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
    }
    public void salvar (Calendario cale){
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
        em.persist(cale);
        em.getTransaction().commit();
        em.close();
    }
    public Calendario consultarPorId(int id) {
      getEntityManager();
      TypedQuery query = em.createQuery("Select id from tbl_livro where id ="+id,Calendario.class);
      List<Calendario> livros = query.getResultList();
      for(int i=0;i<livros.size();i++){
          if(id==livros.get(i).getId()){
              em.close();
              System.out.println("Livro encontrado!");
              return livros.get(i);
          }
      }
      em.close();
      return null;
    }
    public void excluir (int id) throws Exception{
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
        Calendario entity = em.find(Calendario.class, id);
        if(entity!=null){
            em.remove(entity);
        }else{
            throw new Exception("Não existe o id: "+ id);
        }
        em.getTransaction().commit();
        em.close();
    }
    public List<Calendario> buscar() {
      em = EntityManagerUtil.getEntity();
      TypedQuery<Calendario> query = em.createQuery("Select usu from Calendario usu",
                      Calendario.class);
      List<Calendario> lista = query.getResultList();
      em.close();
      return lista;
    }
    public void atualizar (Calendario cale){
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
        em.merge(cale);
        em.getTransaction().commit();
        em.close();
    }
}
