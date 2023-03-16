/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package substituidores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gustavo
 */
public class substituidores {

  public static void main(String Args[]) {

    String nomeArquivo;
    if(Args.length > 1){
      nomeArquivo = Args[1];
    }else{
      System.out.println("Erro: nem um arquivo para ler");
      return;
    }
    

    File arquivo = new File(nomeArquivo);
    
    try {
        String dados = new String(Files.readAllBytes(arquivo.toPath()));
    } catch (IOException ex) {
        Logger.getLogger(substituidores.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    

  }

}







