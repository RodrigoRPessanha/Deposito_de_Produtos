package br.edu.iff.bsi.deposito_de_produtos.controller.funcionario;

import br.edu.iff.bsi.deposito_de_produtos.model.FuncaoEnum;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class FuncaoEnumConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        int code = Integer.parseInt(text);
        FuncaoEnum funcaoEnum = FuncaoEnum.toEnum(code);
        setValue(funcaoEnum);
    }
}

