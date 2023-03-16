
public class substituidores {

  public static void main(String Args[]) {
        
    String nomeArquivo;
    if(Args[1]){
      nomeArquivo = Args[1];
    }else{
      System.out.println("Erro: nem um arquivo para ler");
      exit(1);
    }
    

     
    String dados = new String(Files.readAllBytes(nomeArquivo.toPath));

  }

}
