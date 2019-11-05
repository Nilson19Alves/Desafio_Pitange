/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.desafio.benController;

import Connect.CadastroConnect;
import Connect.IndexConnect;
import br.com.desafio.desafio.Util.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Nilson Alves
 */
@Named
@ViewScoped
public class IndexController implements Serializable {
    
    private String id;
    private String nome;
    private String email;
    private List<Usuario> list;
    private transient boolean editing;
    
    @PostConstruct
    public void init() {
        Map<String, String> initParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        id = initParameterMap.get("id");
        nome = initParameterMap.get("nome");
        email = initParameterMap.get("email");
       
        System.out.println(id);
        System.out.println(nome);
        System.out.println(email);
    }
    
    public List<Usuario> listTable() {
        list = IndexConnect.listUser(14);
        return list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Usuario> getList() {
        return list;
    }

    public void setList(List<Usuario> list) {
        this.list = list;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }
    
    
    
    public String cadastro() {
        return "Cadastro?faces-redirect=true&id=" + id + "&nome=" + nome + "&email=" + email;
    }
    
    public void remove(Usuario usuario) {
        //boolean resultado = IndexConnect.remover(usuario);
        //if (resultado) {
            list.remove(usuario);
        //} else {
            System.out.println("Deu errado");
        //}
    }
}
