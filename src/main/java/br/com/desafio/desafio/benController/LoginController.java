/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.desafio.benController;

import Connect.IndexConnect;
import Connect.LoginConnect;
import br.com.desafio.desafio.Util.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Nilson Alves
 */
@Named
@SessionScoped
public class LoginController implements Serializable{
    
    private String email;
    private String senha;
    private Usuario usuario;

    
    @PostConstruct
    public void init() {
        
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    // Logar Usuario
    public String logar() {
        
        String acesso = null;
        if (email != null && senha != null) {
            usuario = LoginConnect.verificar(email, senha);
            if (usuario != null) {
            //acesso = "index?faces-redirect=true&nome=nilson";
            acesso = "index?faces-redirect=true&id=" + usuario.getId() + "&nome=" + usuario.getNome() + "&email=" + usuario.getEmail();
            } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email ou Senha Incorretos", ""));
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email ou Senha Vazios", ""));
        }
        return acesso;
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuario = null;
        return "Login?faces-redirect=true";
    }
    
    public void test() {
        System.out.println(email);
        System.out.println(senha);
        System.out.println(LoginConnect.getConn());
        System.out.println(LoginConnect.verificar(email, senha));
    }
}
