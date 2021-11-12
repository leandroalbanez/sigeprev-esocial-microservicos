import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propriedades {
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("dados.properties");
		props.load(file);
		return props;
	}
	
	public static void  main(String args[]) throws IOException {
        String login; //Variavel que guardar� o login do servidor.
        String host; //Variavel que guardar� o host do servidor.
        String password; //Vari�vel que guardar� o password do us�ario.
        System.out.println("************Teste de leitura do arquivo de propriedades************");
         
        Properties prop = getProp();
         
        login = prop.getProperty("prop.cert.alias");
        host = prop.getProperty("prop.cert.password");
        password = prop.getProperty("prop.server.password");
         
        System.out.println("Login = " + login);
        System.out.println("Host = " + host);
        System.out.println("Password = " + password);
    }
}