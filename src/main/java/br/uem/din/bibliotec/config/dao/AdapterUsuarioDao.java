package br.uem.din.bibliotec.config.dao;

import br.uem.din.bibliotec.config.model.Usuario;

public class AdapterUsuarioDao extends UsuarioDao{
    Usuario cadUser = new Usuario();

    public AdapterUsuarioDao(Usuario user){
        this.cadUser = user;
        this.cadUser.setAtivo(0);
        this.cadUser.setPermissao(0);
        this.cadUser.setJaativado(0);
    }

    public int cadastrarUsuario() {
        return cadastrarUsuarioBalconista(cadUser);
    }
}
