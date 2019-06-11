package br.uem.din.bibliotec.config.controller;

import br.uem.din.bibliotec.config.dao.UsuarioDao;
import br.uem.din.bibliotec.config.model.Usuario;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.awt.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class UsuarioController implements Serializable {
    private Usuario user = new Usuario("","","","","","","","","","",0,-1,"","");
    private UsuarioDao userDao = new UsuarioDao();
    private int retorno = 0;
    private final String SUCESSO = "green";
    private final String FALHA = "red";

    //contrutores e gets/sets
    public UsuarioController() {}

    public UsuarioController(String login){
        login = new String();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public UsuarioDao getUserDAO() {
        return userDao;
    }

    public void setUserDAO(UsuarioDao userDAO) {
        this.userDao = userDAO;
    }

    public UsuarioDao getUserDao() { return userDao; }

    public void setUserDao(UsuarioDao userDao) { this.userDao = userDao; }

    public String getSUCESSO() { return SUCESSO; }

    public String getFALHA() { return FALHA; }

    public int getRetorno() { return retorno; }

    public void setRetorno(int retorno) { this.retorno = retorno; }

    //chama método de cadastramento de usuários no model
    public String realizarCadastroUsuario() throws SQLException, AWTException {
        //cria usuário inativo e sem permissões
        user.setAtivo(0);
        user.setPermissao(0);
        user.setJaativado(0);

        retorno = userDao.cadastrarUsuario(user);

        if(retorno == 1){
            user.setMsg_autenticacao("Cadastrado com sucesso.");
        }else{
            if(retorno == 0){
                user.setMsg_autenticacao("Cadastro falhou.");
            }else{
                if(retorno == -1){
                    user.setMsg_autenticacao("CPF inválido!");
                }
            }

        }
        return userDao.homePage();
    }

    public String realizarCadastroUsuarioBalconista() throws SQLException, AWTException {
        //cadastra usuário ativo e com permissões
        user.setAtivo(1);
        user.setJaativado(1);

        retorno = userDao.cadastrarUsuario(user);

        if(retorno == 1){
            user.setMsg_autenticacao("Cadastrado com sucesso.");
            user.setColor_msg(SUCESSO);
        }else{
            if(retorno == 0){
                user.setMsg_autenticacao("Cadastro falhou.");
                user.setColor_msg(FALHA);
            }else{
                if(retorno == -1){
                    user.setMsg_autenticacao("CPF inválido!");
                    user.setColor_msg(FALHA);
                }
            }

        }
        return userDao.homePage();
    }

    //chama método de consulta de usuários no model
    public List<Usuario> realizaConsultaUsuario() throws SQLException {
        return userDao.consultarUsuarioBalconista(user, 0);
    }

    public List<Usuario> realizaConsultaUsuariosAtivos () throws SQLException {
        return userDao.consultarUsuarioBalconista(user, 1);
    }

    //chama método de deleção de usuários no model
    public String realizaDelecaoUsuario() throws SQLException{
        retorno = userDao.deletarUsuario(user);

        if(retorno == 1){
            user.setMsg_autenticacao("Retorno: O usuário selecionado foi deletado com sucesso.");
            user.setColor_msg(SUCESSO);
        }else{
            if(retorno == 0){
                user.setMsg_autenticacao("Retorno: A deleção do usuário falhou, contacte o administrador do sistema.");
                user.setColor_msg(FALHA);
            }else{
                user.setMsg_autenticacao("Retorno: O usuário informado não existe.");
                user.setColor_msg(FALHA);
            }
        }
        return userDao.homePage();
    }

    //chama método para editar usuário
    public String realizaEdicaoUsuario() throws SQLException{
        retorno =  userDao.editarUsuario(user);

        if(retorno == 1){
            user.setMsg_autenticacao("Retorno: As informações do usuário foram atualizadas com sucesso.");
            user.setColor_msg(SUCESSO);
        }else{
            if(retorno == 0){
                user.setMsg_autenticacao("Retorno: A operação de alteração do usuário  falharam, contacte o administrador.");
                user.setColor_msg(FALHA);
            }else{
                if(retorno == -1){
                    user.setMsg_autenticacao("Retorno: O usuário informado não existe, edição falhou.");
                    user.setColor_msg(FALHA);
                }else{
                    if(retorno == -2){
                        user.setMsg_autenticacao("Retorno: A edição falhou, pois o CPF informado é inválido.");
                        user.setColor_msg(FALHA);
                    }
                }
            }
        }
        return userDao.homePage();
    }

    //chama métodos para manipulação dos dados cadastrais do próprio usuário
    public String chamaMenuInicial(){
        return userDao.homePage();
    }

    public String realizaAtualizacaoMeusDados(){
        retorno =  userDao.atualizaMeusDados(user);

        if(retorno == 1){
            user.setMsg_autenticacao("Dados atualizados com sucesso!");
            user.setColor_msg(SUCESSO);
        }else{
            if(retorno == 0){
                user.setMsg_autenticacao("Nenhuma alteração identificada!");
                user.setColor_msg(FALHA);
            }else{
                if(retorno == -1){
                    user.setMsg_autenticacao("Falha ao atualizar dados.");
                    user.setColor_msg(FALHA);
                }else{
                    if(retorno == -2){
                        user.setMsg_autenticacao("CPF inválido!");
                        user.setColor_msg(FALHA);
                    }
                }
            }
        }

        return userDao.homePage();
    }

    public String realizaRedefinirSenha() throws NoSuchAlgorithmException {
        retorno = userDao.redefSenha(user);

        if(retorno == 1){
            user.setMsg_autenticacao("Sucesso - Senha enviada no e-mail.");
        }else{
            if(retorno == 0){
                user.setMsg_autenticacao("Falha - Erro ao redefinir senha.");
            }else{
                if(retorno == -1){
                    user.setMsg_autenticacao("Falha - CPF informado é inválido.");
                }else{
                    if(retorno == -2){
                        user.setMsg_autenticacao("Falha - Usuário inativo.");
                    }else{
                        if(retorno == -3){
                            user.setMsg_autenticacao("Falha - Usuário sem permissões.");
                        }else{
                            if(retorno == -4){
                                user.setMsg_autenticacao("Falha - Dados inválidos.");
                            }
                        }
                    }
                }
            }
        }

        return "/gestaoBibliotecas?faces-redirect=true";
    }
}
