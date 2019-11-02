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
import projeto_ppi.pojo.Usuario;

/**
 *
 * @author Seven
 */
public class UsuarioDAO {
    private EntityManager em;
    
    public void salvar (Usuario mensagem){
      em = EntityManagerUtil.getEntity();
      em.getTransaction().begin();
      em.persist(mensagem);
      em.getTransaction().commit();
      em.close();
    }

    public void atualizar (Usuario mensagem){
      em = EntityManagerUtil.getEntity();
      em.getTransaction().begin();
      em.merge(mensagem);
      em.getTransaction().commit();
      em.close();
    }

    public void remover (Long id) throws Exception{
      em = EntityManagerUtil.getEntity();
      em.getTransaction().begin();
      Usuario entity = em.find(Usuario.class, id);
      if(entity!=null){
          em.remove(entity);
      }else{
          throw new Exception("Não a mensagem o id: "+ id);
      }
      em.getTransaction().commit();
      em.close();
    }

    public List<Usuario> buscar() {
      em = EntityManagerUtil.getEntity();
      TypedQuery<Usuario> query = em.createQuery("Select usu from Usuario usu",
                      Usuario.class);
      List<Usuario> lista = query.getResultList();
      em.close();
      return lista;
    }
}
