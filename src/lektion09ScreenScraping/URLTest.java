package lektion09ScreenScraping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

public class URLTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.valutakurser.dk/");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String line;
        while ((line = br.readLine())!=null) {
            if (line.contains("0,3") &&
                    line.contains("currencyItem_actualValueContainer__2xLkB")
                        && line.contains("/valuta/amerikanske-dollar/USD")) {
                System.out.println(line);
            }
        }
    }
}