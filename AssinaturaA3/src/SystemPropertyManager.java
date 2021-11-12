

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Provider;
import java.security.Security;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.SystemUtils;

import br.com.ceramic.assinador.classes.TipoCertificado;
import br.com.ceramic.assinador.classes.TipoCertificadoA3;

public class SystemPropertyManager {

    public static void setProperties(TipoCertificado tipoCertificado, String pass, File cacerts, File clientCert, String certTipo) throws Exception{

        System.clearProperty("javax.net.ssl.keyStore");
        System.clearProperty("javax.net.ssl.keyStorePassword");
        System.clearProperty("javax.net.ssl.trustStore");

        switch (tipoCertificado) {
        case A1:
            System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
            System.setProperty("javax.net.ssl.keyStore", clientCert.getAbsolutePath());
            System.setProperty("javax.net.ssl.keyStorePassword", pass);
            System.setProperty("javax.net.ssl.trustStoreType", "JKS");
            System.setProperty("javax.net.ssl.trustStorePassword", pass);
            System.setProperty("javax.net.ssl.trustStore", cacerts.getAbsolutePath());
            break;
        case A3:

            //String configName;
            //if(SystemUtils.IS_OS_WINDOWS){
            //    configName = "c:\\pkcs11"+certTipo+".cfg";
            //}else{
            //    configName = "/opt/bar/cfg/pkcs11"+certTipo+".cfg";
            //}
            String configName = System.getProperty("user.home") + File.separatorChar + "" + certTipo+".cfg";
            System.out.println(configName);
            File file = new File(configName);
            if(file.isFile()){
                Provider p = new sun.security.pkcs11.SunPKCS11(configName);
                Security.addProvider(p);
            }else{

                TipoCertificadoA3 tipoCertificadoA3 = TipoCertificadoA3.valueOfCodigo(certTipo);

                if(tipoCertificadoA3 != null){
                    File fileConfig = null;
                    if(SystemUtils.IS_OS_WINDOWS){
                        fileConfig = new File("win/"+certTipo+".cfg");

                    }else if(SystemUtils.IS_OS_LINUX){
                        fileConfig = new File("linux/"+certTipo+".cfg");
                    }else{
                        //Editar arquivo de configura??o manualmente indicando o local do dll, e indicar o nome do arquivo no cadastro da cer?mica.
                        throw new Exception("Sistema opera??o ainda n?o suportado pelo sistema! Contate o suporte!");
                    }



                    InputStream in = new FileInputStream(fileConfig);
                    OutputStream out = new FileOutputStream(file);           // Transferindo bytes de entrada para sa?da
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();

                    Provider p = new sun.security.pkcs11.SunPKCS11(file.getAbsolutePath());
                    Security.addProvider(p);

                }else{
                    throw new Exception("Arquivo de configura??o n?o existe em '"+configName+", e n?o configura??o n?o ? reconhecida pelo sistema'!");
                }
            }


        }



    }

}