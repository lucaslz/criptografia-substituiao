/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifradecesar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author nobreack
 */
public class CifraDeCesar {
    
    public static String entrada = "entrada.txt";
    public static String saida = "saida.txt";
    public static File arquivo;
    public static Scanner entradaUsuario = entradaUsuario = new Scanner(System.in);
    public static String frase;
    public static BufferedReader ler;
    public static BufferedWriter escrever;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //frase que o usuario deseja criptografar
        System.out.print("Digite uma frase: ");
        CifraDeCesar.frase = CifraDeCesar.entradaUsuario.nextLine();
        
        //Escrevendo a frase no arquivo
        CifraDeCesar.setFile(CifraDeCesar.entrada);
        CifraDeCesar.escreverNoarquivo(CifraDeCesar.frase);
        
        //Codificando a frase e escrevendo no arquivo
        String palavraCifrada = CifraDeCesar.cifrarDeciraFrase(CifraDeCesar.frase, 1);
        CifraDeCesar.setFile(saida);
        CifraDeCesar.escreverNoarquivo(palavraCifrada);
        System.out.println("Palavra Cifrada: " + palavraCifrada);
        
        //#####################################################################
        //Descifrando a frase
        //Lenado arquivo de saida e pegando a frase cifrada
        CifraDeCesar.setFile(saida);
        String palavraCifradaString = CifraDeCesar.lerArquivo().nextLine();
        String palavraDescifrada = CifraDeCesar.cifrarDeciraFrase(palavraCifradaString, 2);
        System.out.println("Palavra Descifrada: " + palavraDescifrada);
    }
    
    /**
     * pegando uma frase especifica
     * @return 
     */
    public static String getFrase() {
        return frase;
    }
    
    /**
     * setando uma frase
     * @param frase 
     */
    public static void setFrase(String frase) {
        CifraDeCesar.frase = frase;
    }
    
    /**
     * retorna o arquivo
     * @return 
     */
    public static File getFile ()
    {
        return CifraDeCesar.arquivo;
    }
    
    /**
     * seta um arquivo
     * @param nomeArquivo 
     */
    public static void setFile(String nomeArquivo)
    {
       CifraDeCesar.arquivo = new File(nomeArquivo);
    }
    
    /**
     * caso o arquivo não exista cria o mesmo
     * @return 
     */
    public static boolean verificaArquivoCriadoOuNao()
    {   
        if(!CifraDeCesar.getFile().exists()) {
            try {
                CifraDeCesar.getFile().createNewFile();
                return true;
            } catch (IOException ex) {
                printStackTrace("Não foi possivel criar o arquivo");
                return false;
            }
        }
        return true;
    }
    
    /**
     * Escreve em um arquivo
     * @param palavra
     * @return 
     */
    public static boolean escreverNoarquivo(String palavra)
    {
      boolean testeArquivo = CifraDeCesar.verificaArquivoCriadoOuNao();
      
        if (testeArquivo) {
          try {
            CifraDeCesar.escrever = new BufferedWriter(new FileWriter(CifraDeCesar.getFile(), false));
            CifraDeCesar.escrever.append(palavra);
            CifraDeCesar.escrever.flush();
            CifraDeCesar.escrever.close();
          }catch (IOException ex) {
            printStackTrace("Não foi possivel escrever no arquivo");
          }
        }
      return true;
    }
    
    /**
     * Le um arquivo especifico
     * @return 
     */
    public static Scanner lerArquivo()
    {
        boolean testeArquivo = CifraDeCesar.verificaArquivoCriadoOuNao();
        Scanner lerArquivo;
        ArrayList arrayPalavra = new ArrayList();
         
        if (testeArquivo) {
            try {
                CifraDeCesar.ler = new BufferedReader(new FileReader(CifraDeCesar.getFile()));
                lerArquivo = new Scanner(CifraDeCesar.ler);
                return lerArquivo;
            } catch (IOException ex) {
                printStackTrace("Não fpalavraoi possivel escrever no arquivo");            
            }
        }
        return null;
    }
    
    /**
     * Cifrando a Frase
     * @param palavra
     * @param tipo : 1 cifra a palavra | 2 decifra a palavra
     * @return 
     */
    public static String cifrarDeciraFrase(String palavra, int tipo)
    {
        int total = palavra.length();
        char letra;
        int numeroLetra;
        int totalNumeroLetra;
        String palavraCriptografada;
        String aux;
        StringBuilder s = new StringBuilder();
        
        switch (tipo) {
            case 1: {
                for (int i = 0; i < total; i++) {
                    letra = palavra.charAt(i);
                    numeroLetra = (int) letra;
                    totalNumeroLetra = numeroLetra + 2;
                    letra = (char) totalNumeroLetra;
                    s.append(letra);
                }
                break;
            }
            case 2:{
                for (int i = 0; i < total; i++) {
                    letra = palavra.charAt(i);
                    numeroLetra = (int) letra;
                    totalNumeroLetra = numeroLetra - 2;
                    letra = (char) totalNumeroLetra;
                    s.append(letra);
                }
                break;
            }
        }
        palavraCriptografada = s.toString();
        return palavraCriptografada;
    }
}
