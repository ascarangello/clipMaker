
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class URLReader {

  public static void main(String[] args) {
    try {
      String url = "https://therealdeal.com/2018/07/01/real-estate-agent-finds-dead-toronto-billionaires-while-showing-house/";
      Document doc = Jsoup.connect(url).get();
      String title = doc.title();
      Pattern datePattern = Pattern.compile("[1-2]\\d\\d\\d/[0-1]\\d/[0-3]\\d");
      Matcher matcher = datePattern.matcher(url);
      Elements paragraphs = doc.getElementsByTag("p");
      System.out.println("Headline: " + title);
      matcher.find();
      String date = matcher.group();
      System.out.println("Date published: " + date);
      for(Element e : paragraphs) {
        System.out.println("\t" + e.text() + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}


