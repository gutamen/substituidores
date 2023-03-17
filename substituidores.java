

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/*
 * @author gustavo
 *
 */

public class substituidores {
  public static int atualizaçaumLrua;
  public static void main(String Args[]) {
    int tamanhoFrame = 0; 
    atualizaçaumLrua = 10;
    String nomeArquivo, tipoDeSubstituiçao;
    if(Args.length >= 3){
      nomeArquivo = Args[0];
      tipoDeSubstituiçao = Args[1].toLowerCase();
      tamanhoFrame = Integer.parseInt(Args[2]);
      if (Args.length >= 4) {
        atualizaçaumLrua = Integer.parseInt(Args[3]);  
      }
    }else{
      System.out.println("Erro: Argumentos diferentes do necessário");
      return;
    }
    

    File arquivo = new File(nomeArquivo);
    String dados = null;
    try {
        dados = new String(Files.readAllBytes(arquivo.toPath()));
    } catch (IOException ex) {
        Logger.getLogger(substituidores.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    if(dados != null){
      ArrayList endereços = new ArrayList<String>();
      for(int i=0; i < dados.length(); i+=8){
        endereços.add(dados.substring(i, i+8));
        i++;
      }
      for(int i = 0; i < endereços.size(); i++){
        String binario = new String();
        int k = 0;
        String atual =(String) endereços.get(i);

        while(k < 8){
          switch(atual.charAt(k))  
          { 
            case 0x20:
               binario += "0000";
               break;
            case '0':  
               binario += "0000";  
               break;  
            case '1':  
               binario += "0001";  
               break;  
            case '2':  
               binario += "0010";  
               break;  
            case '3':  
               binario += "0011";  
               break;  
            case '4':  
               binario += "0100";  
               break;  
            case '5':  
               binario += "0101";  
               break;  
            case '6':  
               binario += "0110";  
               break;  
            case '7':  
               binario += "0111";  
               break;  
            case '8':  
               binario += "1000";  
               break;  
            case '9':  
               binario += "1001";  
               break;  
            case 'a':  
            case 'A':  
               binario += "1010";  
               break;  
            case 'b':  
            case 'B':  
               binario += "1011";  
               break;  
            case 'c':  
            case 'C':  
               binario += "1100";  
               break;  
            case 'd':  
            case 'D':  
               binario += "1101";  
               break;  
            case 'e':  
            case 'E':  
               binario += "1110";  
               break;  
            case 'f':  
            case 'F':  
               binario += "1111";  
               break;  
            default:  
               System.out.println("Invalid Hexadecimal Digit!");  
               return;  
         }  
         k++;  
        }
        endereços.set(i, binario);
      }


      
      //Nesse ponto o arquivo está convertido para uma lista de Strings com 32 valores
        
    
      if(tipoDeSubstituiçao.equals("lrua")) System.out.println(lruAproximado(endereços, tamanhoFrame));
      if(tipoDeSubstituiçao.equals("lru")) System.out.println(lru(endereços, tamanhoFrame));
        
      /*for(int i = 0; endereços.size() > i; i++){
        System.out.println(endereços.get(i).toString()+ " " + (endereços.get(i).toString().length()));
      }*/
    }
  }

  public static int lru(ArrayList<String> endereços, int tamanhoFrame){
    int erros = 0;
    Stack memoria = new Stack<String>();


    for(int i = 0; i < endereços.size(); i++){
       
      Boolean existe = false;
      int onde;
      for(onde = 0; onde < memoria.size(); onde++){
        String compara1 = (String)memoria.get(onde);
        String compara2 = endereços.get(i).substring(0,19);
        if(compara1.equals(compara2)){
          existe = true;
          break; 
        }
      } 
 

      if(existe){
        
        memoria.remove(onde);
        memoria.push(endereços.get(i).substring(0,19));
        
      }else if(memoria.size() < tamanhoFrame){
        memoria.push(endereços.get(i).substring(0,19));
        erros++;
      }
      else{
        
        if(!memoria.isEmpty()) memoria.remove(0);
        memoria.push(endereços.get(i).substring(0,19));
        erros++;

      } 
    }
    return erros;
  }

  public static int lruAproximado(ArrayList<String> endereços, int tamanhoFrame){
    int erros = 0, atualizaçao = atualizaçaumLrua;
    Stack memoria = new Stack<estrutura>();


    for(int i = 0; i < endereços.size(); i++){
      
      Boolean existe = false;
      int onde;
      for(onde = 0; onde < memoria.size(); onde++){
        String compara1 = ((estrutura)(memoria.get(onde))).endereço;
        String compara2 = endereços.get(i).substring(0,19);
        if(compara1.equals(compara2)){
          existe = true;
          break; 
        }
      } 

      if(existe){
        memoria.remove(onde);
        memoria.push(new estrutura(endereços.get(i).substring(0,19)));

      }
      else if(memoria.size() < tamanhoFrame){
        memoria.push(new estrutura(endereços.get(i).substring(0,19)));
        erros++;
      }
      else{
        int menor = Integer.MAX_VALUE;
        int quemRemove = -1;
        for(int k = 0; memoria.size() > k; k++){
          if(((estrutura)memoria.get(k)).referencia < menor){
            quemRemove = k;
            menor = ((estrutura)memoria.get(k)).referencia; 
          }
        }
        memoria.remove(quemRemove);
        memoria.push(new estrutura(endereços.get(i).substring(0,19)));
        erros++;
      }
      
      if (atualizaçao < 10) atualizaçao++;
      else{
        for(int k = 0; k < memoria.size(); k++){
          if(((estrutura)memoria.get(k)).referencia > 0) ((estrutura)memoria.get(k)).referencia --;
        }
      }
    }
    
    return erros;
  }

}

class estrutura{
  public String endereço = new String();
  public int referencia = 3;  // Considerando 2 bits de referencia 0-3
  public estrutura(String _S){
    this.endereço = _S;
  }
}





