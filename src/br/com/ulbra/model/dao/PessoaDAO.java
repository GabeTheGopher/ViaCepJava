/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ulbra.model.dao;

import br.com.ulbra.connection.ConnectionFactory;
import br.com.ulbra.model.bean.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author glope
 */
public class PessoaDAO {
    
    public Pessoa save(Pessoa pessoa){
        EntityManager em = new ConnectionFactory().getConnection();
        
        try{
            em.getTransaction().begin();
            if(pessoa.getId() == null){
                em.persist(pessoa);
                JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
            }else{
                em.merge(pessoa);
                JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
            }
            em.getTransaction().commit();
            
        } catch(Exception e) {
            System.err.println(e);
            em.getTransaction().rollback();            
        } finally {
            em.close();
        }
            
        return pessoa;  
    }

    public Pessoa findById(Integer id){
        
        
        EntityManager em = new ConnectionFactory().getConnection();
        Pessoa pessoa = null;
        
        try{
            pessoa = em.find(Pessoa.class, id);  
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return pessoa;
    }
    
    public List<Pessoa> findAll(){
         EntityManager em = new ConnectionFactory().getConnection();
         List<Pessoa> pessoas = null;
         
         try{
             
            pessoas   = em.createQuery("from Pessoa p").getResultList();
             
         }catch(Exception e){
            System.err.println(e); 
         }finally{
            em.close();
         }
         return pessoas;
    }
    
    public Pessoa remove(Integer id){
        EntityManager em = new ConnectionFactory().getConnection();
        Pessoa pessoa = null;
        
        try{
            pessoa = em.find(Pessoa.class, id);
            
            em.getTransaction().begin();
            em.remove(pessoa);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Registro apagado com sucesso!");
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        return pessoa;
    }
}
