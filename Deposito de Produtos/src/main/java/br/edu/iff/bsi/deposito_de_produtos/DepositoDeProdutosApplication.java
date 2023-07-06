package br.edu.iff.bsi.deposito_de_produtos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DepositoDeProdutosApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(DepositoDeProdutosApplication.class, args);
        }catch (Exception e){
            System.out.println("Message = " + e.getMessage());
        }
    }

}
