import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> s = new ArrayList<>();

    public String handleRequest(URI url) 
    {
        if (url.getPath().equals("/")) 
        {
            return String.format("Hi!! \n use add and Search");
        } 
        else if (url.getPath().equals("/add")) 
        {
            if (url.getPath().contains("s")) 
            {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s"))
                 {
                    s.add(parameters[1]);
                }
            }
            return "404 Not Found!";
        }
        else if (url.getPath().equals("/search")) 
        {
            if (url.getPath().contains("s")) 
            {
                String[] parameters = url.getQuery().split("=");
                if (parameters[1].equals("app"))
                 {
                    for(String word : s)
                    {
                        System.out.println(word);
                    }
                }
            }
            return "404 Not Found!";
        }
         else 
        {
            return "404 Not Found!";
        }
    }
}


public class SearchEngine 
{
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
