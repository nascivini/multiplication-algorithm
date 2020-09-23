package main.java;

import java.util.Scanner;

public class Multiplication {

    public static void main(String[] args){
        init();
    }

    public static void init(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Algotítimo de multiplicação de binários - iniciando ...");
        System.out.println("Insira o multiplicando: ");

        String multiplicando = scanner.next();

        System.out.println("Insira o multiplicador (Deverá ter o mesmo tamanho em bits do multiplicando): ");
        String multiplicador = scanner.next();

        checkInputs(multiplicador, multiplicando);
        multiplyList(multiplicador, multiplicando);
    }

    private static void checkInputs(String multiplicador, String multiplicando){
        if(multiplicador == null || multiplicando == null || multiplicador.length() != multiplicando.length()){
            System.out.println("Os binários inseridos possuem tamanhos diferentes! Encerrando ...");
            System.exit(1);
        }
    }

    private static void multiplyList(String multiplicador, String multiplicando){
        int bitLength = multiplicando.length();
        char extraBit = '0';
        String ppa = leftPad("", bitLength, "0");
        String ppq = multiplicando;

        for(int i = bitLength - 1; i > 0; i --){
            if(multiplicando.charAt(i) == '1'){
                ppa = leftPad(sumBinaries(ppa, multiplicador), bitLength, "0");
                if(ppa.length() > bitLength){
                    extraBit = ppa.charAt(0);
                    ppa = ppa.substring(1);
                }
            }
            String result = shiftPad(extraBit + ppa + ppq);
            extraBit = '0';
            ppa = result.substring(1, bitLength + 1);
            ppq = result.substring(bitLength + 1, bitLength * 2 + 1);
        }
        System.out.println(ppa + ppq);
    }

    private static String sumBinaries(String bin1, String bin2){
        int i = Integer.parseInt(bin1, 2);
        int j = Integer.parseInt(bin2, 2);
        return Integer.toBinaryString(j + i);
    }

    public static String leftPad(String input, int length, String fill){
        String pad = String.format("%"+length+"s", "").replace(" ", fill) + input.trim();
        return pad.substring(pad.length() - length, pad.length());
    }

    private static String shiftPad(String allBits){
        allBits = allBits.substring(0, allBits.length() - 1);
        allBits = "0" + allBits;
        return allBits;
    }
}
