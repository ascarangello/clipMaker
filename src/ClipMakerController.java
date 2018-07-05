import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClipMakerController implements ActionListener {
  private ClipMakerModel model;
  private ClipMakerView view;

  public ClipMakerController(ClipMakerModel model, ClipMakerView view) {
    this.model = model;
    this.view = view;
    this.view.setCommandButtonListener(this);
  }

  public void processCommand(String URL, String clientName) {
    try {
      model = new ClipMakerModel(URL, clientName);
      model.go();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String URL = view.getURL();
    String clientName = view.getClientName();
    try {
      this.processCommand(URL, clientName);
    }
    catch (Exception E) {
      E.printStackTrace();
    }

  }
}
