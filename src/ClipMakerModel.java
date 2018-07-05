import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.vml.ObjectFactory;
import org.docx4j.wml.Br;
import org.docx4j.wml.Tabs;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static org.docx4j.openpackaging.packages.WordprocessingMLPackage.createPackage;

public class ClipMakerModel {

  private String url;
  private String headline;
  private String date;
  private String clientName;
  private ArrayList<String> articleContent;

  public String getUrl() {
    return url;
  }

  public String getHeadline() {
    return headline;
  }

  public String getDate() {
    return date;
  }

  public String getClientName() {
    return clientName;
  }

  public ArrayList<String> getArticleContent() {
    return articleContent;
  }

  public ClipMakerModel() {
    this.url = null;
    this.clientName = null;
    this.headline = null;
    this.articleContent = null;
    this.date = null;
  }

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
      if (e.text() != "") {
        articleContent.add(e.text());
      }
    }
    Pattern datePattern = Pattern.compile("[1-2]\\d\\d\\d/[0-1]\\d/[0-3]\\d");
    Matcher matcher = datePattern.matcher(url);
    matcher.find();
    this.date = matcher.group();
  }

  public void go() {
    WordprocessingMLPackage wordMLPackage = null;
    try {
      wordMLPackage = WordprocessingMLPackage.createPackage();
      MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
      mdp.addParagraphOfText(this.clientName);
      mdp.addParagraphOfText(this.headline);
      mdp.addParagraphOfText(this.date);
      for (String s : this.articleContent) {
        mdp.addParagraphOfText(s);
      }
    } catch (InvalidFormatException e) {
      e.printStackTrace();
    }
    // Save it
    try {
      wordMLPackage.save(new java.io.File("newClipping.docx"));
    } catch (Docx4JException e) {
      e.printStackTrace();
    }
  }


}
