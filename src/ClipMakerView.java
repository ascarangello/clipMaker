import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ClipMakerView extends JFrame {
  private JButton goButton;
  private JTextField urlField;
  private JTextField clientField;
  private JLabel urlLabel;
  private JLabel clientLabel;
  private JPanel URLPanel;
  private JPanel clientPanel;

  public ClipMakerView() {
    super();
    this.setTitle("Clip Maker V1.0");
    this.setSize(300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    URLPanel = new JPanel();
    URLPanel.setLayout(new FlowLayout());

    clientPanel = new JPanel();
    clientPanel.setLayout(new FlowLayout());

    this.add(URLPanel, BorderLayout.NORTH);
    this.add(clientPanel, BorderLayout.CENTER);
    goButton = new JButton("Create Clip");
    this.add(goButton, BorderLayout.SOUTH);


    urlLabel = new JLabel("Enter a valid article URL here:");
    URLPanel.add(urlLabel);

    urlField = new JTextField(15);
    URLPanel.add(urlField);

    clientLabel = new JLabel("Enter Client Name Here:");
    clientPanel.add(clientLabel);

    clientField = new JTextField(15);
    clientPanel.add(clientField);

    this.pack();
    this.setVisible(true);

  }

  public void setCommandButtonListener(ActionListener e) {
    goButton.addActionListener(e);
  }

  public String getURL() {
    return this.urlField.getText();
  }

  public String getClientName() {
    return this.clientField.getText();
  }
}


