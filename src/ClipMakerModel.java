import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ClipMakerModel {

  private String url;
  private String headline;
  private String date;
  private String clientName;
  private ArrayList<String> articleContent;

  public ClipMakerModel(String url, String clientName) {
    this.url = url;
    this.clientName = clientName;
    Document doc = null;
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.headline = doc.title();
    articleContent = new ArrayList<String>();
    Elements paragraphs = doc.getElementsByTag("p");
    for (Element e : paragraphs) {
      articleContent.add(e.text());
    }
    Pattern datePattern = Pattern.compile("[1-2]\\d\\d\\d/[0-1]\\d/[0-3]\\d");
    Matcher matcher = datePattern.matcher(url);
    matcher.find();
    this.date = matcher.group();
  }



}
