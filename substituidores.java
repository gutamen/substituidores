/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class substituidores {

  public static void main(String Args[]) {

    String nomeArquivo;
    if(Args.length > 0){
      nomeArquivo = Args[0];
    }else{
      System.out.println("Erro: nem um arquivo para ler");
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
      //System.out.println(dados);
      ArrayList endereços = new ArrayList<String>();
      for(int i=0; i < dados.length(); i+=8){
        endereços.add(dados.substring(i, i+7));
        i++;
      }
      for(int i = 0; i < endereços.size(); i++){
        String binario = null;
        int k = 0;
        while(k < 8){
          switch(endereços.get(i).substring(k,k))  
          {  
            case '0':  
               System.out.print("0000");  
               break;  
            case '1':  
               System.out.print("0001");  
               break;  
            case '2':  
               System.out.print("0010");  
               break;  
            case '3':  
               System.out.print("0011");  
               break;  
            case '4':  
               System.out.print("0100");  
               break;  
            case '5':  
               System.out.print("0101");  
               break;  
            case '6':  
               System.out.print("0110");  
               break;  
            case '7':  
               System.out.print("0111");  
               break;  
            case '8':  
               System.out.print("1000");  
               break;  
            case '9':  
               System.out.print("1001");  
               break;  
            case 'a':  
            case 'A':  
               System.out.print("1010");  
               break;  
            case 'b':  
            case 'B':  
               System.out.print("1011");  
               break;  
            case 'c':  
            case 'C':  
               System.out.print("1100");  
               break;  
            case 'd':  
            case 'D':  
               System.out.print("1101");  
               break;  
            case 'e':  
            case 'E':  
               System.out.print("1110");  
               break;  
            case 'f':  
            case 'F':  
               System.out.print("1111");  
               break;  
            default:  
               System.out.println("Invalid Hexadecimal Digit!");  
               return;  
         }  
         k++;  
        }
      }
    }
  }

}







