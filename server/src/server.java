import java.io.*;
import java.net.*;
import java.security.KeyStore;
import javax.net.*;
import javax.net.ssl.*;
import javax.security.cert.X509Certificate;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.ArrayList;

public class server implements Runnable {
    private ServerSocket serverSocket = null;
    private static int numConnectedClients = 0;

    private static Hashtable<String, ArrayList<String>> medicalRecords;
    private static ArrayList<String> doctorList;

    private Router router;

    public server(ServerSocket ss) throws IOException {
        medicalRecords = new Hashtable<String, ArrayList<String>>();
        doctorList = new ArrayList<String>();
        doctorList.add("Doctor");
        doctorList.add("info1");
        medicalRecords.put("CN=Oscar Fridh (os5614fr-s)/Filip Myhrén (fi8057my-s)/Lucas Edlund (lu6512ed-s)/Nils Stridbeck (ni8280st-s), OU=LTH, O=LTH, L=Lund, ST=SE, C=SE", doctorList);
        serverSocket = ss;

        router = createRouter();

        newListener();
    }

    private Router createRouter() {
        Doctor doctor = new Doctor(1, "Division 1");
        Nurse nurse = new Nurse(1, "Division 1");

        User user = doctor;
        InMemoryMedicalReccordRepository repository = new InMemoryMedicalReccordRepository();
        repository.create(doctor, nurse, new Patient(1), "Medical reccord 1 for patient 1");
        repository.create(doctor, nurse, new Patient(1), "Medical reccord 2 for patient 1");
        repository.create(doctor, nurse, new Patient(2), "Medical reccord 1 for patient 2");
        MedicalReccordController medicalReccordController = new MedicalReccordController(user, repository);
        return new Router(medicalReccordController);
    }

    public void run() {
        try {
            SSLSocket socket=(SSLSocket)serverSocket.accept();
            newListener();
            SSLSession session = socket.getSession();
            X509Certificate cert = (X509Certificate)session.getPeerCertificateChain()[0];
            String subject = cert.getSubjectDN().getName();
            String issuer = cert.getIssuerDN().getName();
            BigInteger serial = cert.getSerialNumber();
            String title = medicalRecords.get(subject).get(0);
    	    numConnectedClients++;
            System.out.println("client connected");
            System.out.println("client name (cert subject DN field): " + subject);
            System.out.println("certificate issuer:\n" + issuer + "\n");
            System.out.println("certificate serial number:\n" + serial + "\n");
            System.out.println("Titel = " + title);
            System.out.println(numConnectedClients + " concurrent connection(s)\n");


            PrintWriter out = null;
            BufferedReader in = null;
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String clientMsg = null;
            while ((clientMsg = in.readLine()) != null) {
                String response = router.handleRequest(clientMsg);
                System.out.println("received '" + clientMsg + "' from client");
                System.out.println("sending '" + response + "' to client...");
                out.println(response);
                out.flush();
                System.out.println("done\n");
			}
			in.close();
			out.close();
			socket.close();
    	    numConnectedClients--;
            System.out.println("client disconnected");
            System.out.println(numConnectedClients + " concurrent connection(s)\n");
		} catch (IOException e) {
            System.out.println("Client died: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    private void newListener() { (new Thread(this)).start(); } // calls run()

    public static void main(String args[]) {
        System.out.println("\nServer Started\n");
        int port = -1;
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }
        String type = "TLS";
        try {
            ServerSocketFactory ssf = getServerSocketFactory(type);
            ServerSocket ss = ssf.createServerSocket(port);
            ((SSLServerSocket)ss).setNeedClientAuth(true); // enables client authentication
            new server(ss);
        } catch (IOException e) {
            System.out.println("Unable to start Server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static ServerSocketFactory getServerSocketFactory(String type) {
        if (type.equals("TLS")) {
            SSLServerSocketFactory ssf = null;
            try { // set up key manager to perform server authentication
                SSLContext ctx = SSLContext.getInstance("TLS");
                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
                KeyStore ks = KeyStore.getInstance("JKS");
				KeyStore ts = KeyStore.getInstance("JKS");
                char[] password = "password".toCharArray();

                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                ks.load(classloader.getResourceAsStream("serverkeystore"), password);  // keystore password (storepass)
                ts.load(classloader.getResourceAsStream("servertruststore"), password); // truststore password (storepass)
                kmf.init(ks, password); // certificate password (keypass)
                tmf.init(ts);  // possible to use keystore as truststore here
                ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
                ssf = ctx.getServerSocketFactory();
                return ssf;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return ServerSocketFactory.getDefault();
        }
        return null;
    }

}
