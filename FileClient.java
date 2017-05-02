import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;



public class FileClient {

    private static Socket sock;
    private static String fileName;
    private static BufferedReader stdin;
    private static PrintStream os;
   
    

    public static void main(String[] args) throws IOException {
        try {
            sock = new Socket("localhost", 4444);
            
            stdin = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            System.err.println("Cannot connect to the server, try again later.");
            System.exit(1);
        }

        os = new PrintStream(sock.getOutputStream());

        try {
             
                
                System.err.print("Enter file name to download: ");
                fileName = stdin.readLine();
                os.println(fileName);
                long startTime = System.nanoTime();
                receiveFile(fileName,startTime);
                
        
        } catch (Exception e) {
            System.err.println("not valid input");
        }


        sock.close();
    }

  

   
    public static void receiveFile(String fileName,long startTime) {
        try {
            int bytesRead;
            InputStream in = sock.getInputStream();

            DataInputStream clientData = new DataInputStream(in);

            fileName = clientData.readUTF();
            OutputStream output = new FileOutputStream(("received_from_server_" + fileName));
            long size = clientData.readLong();
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }

            output.close();
            in.close();
             
             BufferedReader br = new BufferedReader(new FileReader(fileName));
    	     String line = null;
             System.out.println("The contents of the file are:\n");
             while ((line = br.readLine()) != null) {
                System.out.println(line);
                }
            System.out.println("File "+fileName+" received from Server.");
            long endTime   = System.nanoTime();
	    long totalTime = endTime - startTime;   
            System.out.println("RTT:"+ totalTime+"nanoseconds");
            
        } catch (IOException ex) {
            Logger.getLogger(CLIENTConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}