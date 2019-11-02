/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_ppi.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import projeto_ppi.pojo.Calendario;
import projeto_ppi.pojo.Manuais;
import projeto_ppi.pojo.Operacao;

/**
 *
 * @author Seven
 */
public class ManuaisDAO {
    private EntityManager em;
    
    public ManuaisDAO (){
        
    }
    public void getEntityManager(){
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
    }
    public void salvar (Manuais manual){
        EntityManager em = EntityManagerUtil.getEntity();
        em.getTransaction().begin();
        em.persist(manual);
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
        Manuais entity = em.find(Manuais.class, id);
        if(entity!=null){
            em.remove(entity);
        }else{
            throw new Exception("Não existe o id: "+ id);
        }
        em.getTransaction().commit();
        em.close();
    }
    
    public List<Manuais> buscar() {
      em = EntityManagerUtil.getEntity();
      TypedQuery<Manuais> query = em.createQuery("Select man from Manuais man",
                      Manuais.class);
      List<Manuais> lista = query.getResultList();
      em.close();
      return lista;
    }
}
