package org.newlanguageservice.rlang;


public class GrammarValidationError
{
	private final int mCharPositionInLine;

	private final String mMessage;

	private int mLine;

	private int mOffset;

	public GrammarValidationError(int pLine, int pCharPositionInLine,int pOffset,String pMessage)
	{
		mLine = pLine;
		mCharPositionInLine = pCharPositionInLine;
		mOffset = pOffset;
		mMessage = pMessage;
		
	}

	
	
	public int getOffset() {
		return mOffset;
	}

	public int getCharPositionInLine() {
		return mCharPositionInLine;
	}



	public int getLine() {
		return mLine;
	}

	public String getMessage() {
		return mMessage;
	}

}
