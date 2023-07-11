package br.edu.iff.bsi.deposito_de_produtos.model;

public enum FuncaoEnum {
    GERENTE_DE_DEPOSITO(1),
    ASSOCIADO_DE_DEPOSITO(2),
    SUPERVISOR_DE_DEPOSITO(3),
    OPERARIO_DE_DEPOSITO(4);

    private final int code;

    private FuncaoEnum(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static FuncaoEnum toEnum(int code){
        for(FuncaoEnum f : FuncaoEnum.values()){
            if(f.getCode() == code){
                return f;
            }
        }
        return null;
    }
}
