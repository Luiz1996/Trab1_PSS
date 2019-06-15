package br.uem.din.bibliotec.config.services;

public class FormataRg {
    public String formataRg(String rg) {
        if (rg.trim() == null) {
            return " ";
        } else {
            String[] rgSeparado =  new String[4];

            rgSeparado[0] = rg.trim().substring(0,2);
            rgSeparado[1] = rg.trim().substring(2,5);
            rgSeparado[2] = rg.trim().substring(5,8);
            rgSeparado[3] = rg.trim().substring(8,9);

            return rgSeparado[0].concat(".") + rgSeparado[1].concat(".") + rgSeparado[2].concat("-") + rgSeparado[3];
        }
    }
}
