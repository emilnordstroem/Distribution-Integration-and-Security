package lektion2HeaderLine;

import java.net.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HeaderLines {

    // Bruges til at vise Header linjer for et URL

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://dis.students.dk/example1.php");
        URLConnection conn = url.openConnection();
        Map map = conn.getHeaderFields();
        Set set = map.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        String str;
        InputStreamReader r = new InputStreamReader(url.openStream());
        BufferedReader in = new BufferedReader(r);
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }
        in.close();
    }
}
