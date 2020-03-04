import java.io.*;
import java.net.*;
import java.security.KeyStore;
import javax.net.*;
import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

public class server implements Runnable {
    private ServerSocket serverSocket = null;
    private static int numConnectedClients = 0;

    private InMemoryMedicalReccordRepository medicalReccordRepository;
    private InMemoryUserRepository userRepository;

    public server(ServerSocket ss) throws IOException {
        medicalReccordRepository = new InMemoryMedicalReccordRepository();
        userRepository = new InMemoryUserRepository();
        userRepository.add(new Patient(1));
        userRepository.add(new Patient(2));
        userRepository.add(new Doctor(3, "Division 1"));
        userRepository.add(new Doctor(4, "Division 2"));
        userRepository.add(new Nurse(5, "Division 1"));
        userRepository.add(new Nurse(6, "Division 2"));
        userRepository.add(new Government(7));

        serverSocket = ss;
        newListener();
    }

    private User authenticateUser(int id) {
        return userRepository.get(id);
    }

    public void run() {
        try {
            SSLSocket socket=(SSLSocket)serverSocket.accept();
            newListener();
            SSLSession session = socket.getSession();
            X509Certificate cert = (X509Certificate)session.getPeerCertificateChain()[0];

            // Authenticate user
            String subject = cert.getSubjectDN().getName(); // CN=...
            int certificateId = Integer.parseInt(subject.substring(3));
            User user = userRepository.get(certificateId);
            if (user == null) {
                System.out.println("No such user in database");
                return;
            }

            Router router = new Router(new MedicalReccordController(user, medicalReccordRepository, userRepository, new AuditLogger()));

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
            //System.out.println(numConnectedClients + " concurrent connection(s)\n"); // Hiding for now, since it's not working properly.
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
