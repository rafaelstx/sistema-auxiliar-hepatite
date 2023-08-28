package com.myFuzzyProject;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class TippingClass {
    public static void main(String[] args) {
        // Carregue o arquivo FCL com as regras fuzzy
        String filename = "tipper.fcl";
        FIS fis = FIS.load(filename);
        
        ///////////////////////////////////////////
        // Insira os valores das variaveis		 //
        int nausea_valor = 2;       // De 0 a 10 //
        int vomito_valor = 2;       // De 0 a 10 //
        int dorAbdominal_valor = 10;// De 0 a 10 //
        int febre_valor = 7;        // De 0 a 10 //
        int amarelamento_valor = 0; // De 0 a 10 //
        int frequencia_dias = 2;    // De 0 a 7  //
        ///////////////////////////////////////////
        
        // Verifique se o arquivo FCL foi carregado corretamente
        if (fis == null) {
            System.err.println("Erro ao carregar o arquivo FCL: " + filename);
            return;
        }

        // Obtenha o bloco de funções fuzzy do arquivo FCL
        FunctionBlock functionBlock = fis.getFunctionBlock(null);
        

        // Defina os valores das variáveis de entrada
        functionBlock.setVariable("nausea", nausea_valor * 10);
        functionBlock.setVariable("vomito", vomito_valor * 10);
        functionBlock.setVariable("dorAbdominal", dorAbdominal_valor * 10);
        functionBlock.setVariable("febre", febre_valor * 10);
        functionBlock.setVariable("amarelamento", amarelamento_valor * 10);
        functionBlock.setVariable("frequenciaSintoma", frequencia_dias * 14);

        // Avalie as regras fuzzy
        functionBlock.evaluate();

        // Obtenha a probabilidade de hepatite viral
        double probabilidadeHepatite = functionBlock.getVariable("probabilidadeHepatite").defuzzify();

        System.out.println("Probabilidade de hepatite viral: " + probabilidadeHepatite);
        
        if(probabilidadeHepatite < 30) {
        	System.out.println("\nProbabilidade de hepatite baixa");
        } else if(probabilidadeHepatite < 50) {
        	System.out.println("\nProbabilidade de hepatite média");
        	}else System.out.println("\nProbabilidade de hepatite alta");
    }
}
