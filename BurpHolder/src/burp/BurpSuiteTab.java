package burp;

import burp.ITab;
import burp.IBurpExtenderCallbacks;
import java.awt.Component;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BurpSuiteTab extends javax.swing.JPanel implements ITab {
    IBurpExtenderCallbacks mCallbacks;
    String tabName; 
    JPanel userDefinedPanel;

    public BurpSuiteTab(String tabName, IBurpExtenderCallbacks callbacks) {
	this.tabName = tabName;
        mCallbacks = callbacks;
        
        mCallbacks.customizeUiComponent(this);
        mCallbacks.addSuiteTab(this);
    }
    
    public void addComponent(JPanel customPanel) {
        this.add(customPanel);
        this.revalidate();
        this.doLayout();
    }
    
    @Override
    public String getTabCaption() {
	return tabName;
    }

    @Override
    public Component getUiComponent() {
	return this;
    }
}