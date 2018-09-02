package org.newlanguageservice.rlang;

import org.antlr.v4.runtime.ANTLRInputStream;

/**
 * 
 * @author vkozhaev
 *
 */
public class CustomInputStream extends ANTLRInputStream {

	private String mInput;


	public CustomInputStream(String pInput) {
		super(pInput);
		this.mInput = pInput;
	}
	
	
	public String getInput() {
		return mInput;
	}

}
