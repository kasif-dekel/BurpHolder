package burp;

import burp.IBurpExtender;
import burp.IExtensionStateListener;
import burp.IHttpListener;
import burp.ITab;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import java.awt.Component;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import burp.IniParser;

public class BurpExtender implements IBurpExtender, IExtensionStateListener, IHttpListener, ITab { 
	
	protected String mPluginName = "BurpHolder";
    protected String mUsageStatement = mPluginName + " Helps you to hold several 'variables' inside BurpSuite";
    protected IBurpExtenderCallbacks mCallbacks;
    protected IExtensionHelpers mHelper;
    protected PrintWriter mStdOut;
    protected PrintWriter mStdErr;
    
    protected BurpSuiteTab mTab;
    protected BurpHolderTab component;

    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        mCallbacks = callbacks;
        mHelper = mCallbacks.getHelpers();
        
        callbacks.setExtensionName(mPluginName);
        mStdOut = new PrintWriter(callbacks.getStdout(), true);
        mStdErr = new PrintWriter(callbacks.getStderr(), true);

        callbacks.registerHttpListener(this);
        callbacks.registerExtensionStateListener(this);
        
        component = new BurpHolderTab(mCallbacks);
        mCallbacks.customizeUiComponent(component);
        
        mTab = new BurpSuiteTab(mPluginName, mCallbacks);
        mTab.add(component);
        
        mCallbacks.customizeUiComponent(mTab);
        mCallbacks.addSuiteTab(mTab);
        mStdOut.println(mUsageStatement);
    }
    
    
    @Override
    public String getTabCaption() {
        return mPluginName;
    }

    @Override
    public Component getUiComponent() {
        return mTab;
    }
    
	@SuppressWarnings("unused")
	@Override
	public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
		if(messageIsRequest) { 
			String Request = null;
			try {
				Request = new String(messageInfo.getRequest(), "UTF-8"); // TODO: problematic ? should let user choose encoding ?
			} catch (UnsupportedEncodingException e) {
				mStdErr.println("[error] BurpHolder - Something went wrong: ");
				mStdErr.println(e.getStackTrace());
				return;
			}
			if(Request == null) { 
				mStdErr.println("[error] BurpHolder - Could'nt Get Request Data");
				return;
			}
			messageInfo.setRequest(ProcessRequestReplacements(Request).getBytes());
			
		}
		
	}

	public String ProcessRequestReplacements(String request) {
		String RawIni = component.getTextData();
		HashMap<String, String> properties= null;
        try {
        	properties = IniParser.ParseIni(RawIni, "=");
		} catch (IOException e) {
			mStdErr.println("[error] BurpHolder - Something went wrong: ");
			mStdErr.println(e.getStackTrace());
			return request;
		}
        if(properties==null) { 
        	mStdErr.println("[error] BurpHolder - Could'nt get ini settings");
        	return request;
        }
        @SuppressWarnings("rawtypes")
		Iterator it = properties.entrySet().iterator();
        while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry)it.next();
            request = request.replace("[["+pair.getKey()+"]]", (String) pair.getValue());
            it.remove(); // avoids ConcurrentModificationException
        }
        
		return request;
		
	}

	@Override
	public void extensionUnloaded() {
		// TODO
		
	}

}