package burp;

import javax.swing.JPanel;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.Font;

public class BurpHolderTab extends JPanel {

	private static final long serialVersionUID = 1L;
	IBurpExtenderCallbacks mCallbacks;
	private TextArea textArea;
    public BurpHolderTab(IBurpExtenderCallbacks callbacks) {
    	
    	textArea = new TextArea();
    	textArea.setText(
    					"// please use this ini-like box to define your variables\r\n"
    					+"// variables can be defined this way:\r\n\r\n"
    					+"plugin = \"BurpHolder\"\r\n\r\n"
    					+"// and can be accessed by any REQUEST window of burp like this: \r\n"
    					+"// GET /index.php?id=[[plugin]] \r\n"
    					+"// including the repeater, proxy, intruder, scanner, etc..\r\n"
    				);
    	textArea.setBackground(new Color(0, 0, 0));
    	textArea.setFont(new Font("Symbol", Font.PLAIN, 18));
    	textArea.setForeground(Color.GREEN);
    	add(textArea);
    	mCallbacks = callbacks;
    }
    
    public String getTextData() { 
    	return textArea.getText();
    }
}
