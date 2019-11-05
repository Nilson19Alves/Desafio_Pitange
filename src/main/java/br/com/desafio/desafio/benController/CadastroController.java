/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.desafio.benController;

import Connect.CadastroConnect;
import br.com.desafio.desafio.Util.Telefone;
import br.com.desafio.desafio.Util.Usuario;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Nilson Alves
 */
@Named
@ViewScoped
public class CadastroController implements Serializable{
    
    private String nome;
    private String email;
    private String senha;
    private int ddd_01;
    private int ddd_02;
    private String numero_01;
    private String numero_02;
    
    private String idUser;
    private String nomeUser;
    private String emailUser;

    
    @PostConstruct
    public void init() {
       Map<String, String> initParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
       idUser = initParameterMap.get("id");
       nomeUser = initParameterMap.get("nome");
       emailUser = initParameterMap.get("email");
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getDdd_01() {
        return ddd_01;
    }

    public void setDdd_01(int ddd_01) {
        this.ddd_01 = ddd_01;
    }

    public int getDdd_02() {
        return ddd_02;
    }

    public void setDdd_02(int ddd_02) {
        this.ddd_02 = ddd_02;
    }

    public String getNumero_01() {
        return numero_01;
    }

    public void setNumero_01(String numero_01) {
        this.numero_01 = numero_01;
    }

    public String getNumero_02() {
        return numero_02;
    }

    public void setNumero_02(String numero_02) {
        this.numero_02 = numero_02;
    }
    
    
    
    public String cadastrarUser() {
        String resultado = null;
        Usuario usuario = new Usuario();
        Telefone telefone = new Telefone();
        System.out.println(nome);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        
        telefone.setDdd_01(ddd_01);
        telefone.setNumero_01(numero_01);
        telefone.setDdd_02(ddd_02);
        telefone.setNumero_02(numero_02);
        
        usuario.setTelefone(telefone);
        
        boolean cadastrado = CadastroConnect.novo(usuario);
        if (cadastrado) {
            resultado = "index?faces-redirect=true&id=" + idUser + "&nome=" + nomeUser + "&email=" + emailUser;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um problema ao cadastrar o usuario", ""));
            
        }
        return resultado;
    }
}
