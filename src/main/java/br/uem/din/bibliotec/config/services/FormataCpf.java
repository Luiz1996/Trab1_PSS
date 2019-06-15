package br.uem.din.bibliotec.config.services;

public class FormataCpf {
    public String formataCpf(String cpf) {
        if (cpf.trim() == null) {
            return " ";
        } else {
            String[] cpfSeparado =  new String[4];

            cpfSeparado[0] = cpf.trim().substring(0,3);
            cpfSeparado[1] = cpf.trim().substring(3,6);
            cpfSeparado[2] = cpf.trim().substring(6,9);
            cpfSeparado[3] = cpf.trim().substring(9,11);

            return cpfSeparado[0].concat(".") + cpfSeparado[1].concat(".") + cpfSeparado[2].concat("-") + cpfSeparado[3];
        }
    }
}
