import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

public class LerKeystore {
	public static void main(String[] args) {
		File file;
		InputStream is;
		try {
			file = new File("C:\\Esocial\\Certificados\\jssecacerts");
			is = new FileInputStream(file);
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			String password = "changeit";
			keystore.load(is, password.toCharArray());

			Enumeration<String> enumeration = keystore.aliases();
			while (enumeration.hasMoreElements()) {
				String alias = enumeration.nextElement();
				System.out.println("alias name: " + alias);
				Certificate certificate = keystore.getCertificate(alias);
				System.out.println(certificate.toString());

			}

		} catch (java.security.cert.CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
