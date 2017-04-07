package UIS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by JakeDesktop on 4/6/2017.
 */
public class GUICommandDisplay extends Component implements ActionListener
{
    private JTextField tField;
    private JTextArea tArea;
    private JTabbedPane tabbedPane;

    public JTextField gettField()
    {
        return tField;
    }

    public JTextArea gettArea()
    {
        return tArea;
    }

    public JPanel getCommandDisplay()
    {
        return commandDisplay;
    }

    private JPanel commandDisplay;

    public GUICommandDisplay(JTabbedPane tabbedPane)
    {
        this.tabbedPane = tabbedPane;

        this.tField = new JTextField(20);
        tField.addActionListener(this);

        this.tArea = new JTextArea(5, 20);
        tArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tArea);

        this.commandDisplay = new JPanel();
        commandDisplay.add(tField);
        commandDisplay.add(scrollPane);
    }

    public void actionPerformed(ActionEvent evt)
    {
        String command = tField.getText();
        tArea.append(command + "\n");
        tField.selectAll();
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        tArea.setCaretPosition(tArea.getDocument().getLength());
    }
}
