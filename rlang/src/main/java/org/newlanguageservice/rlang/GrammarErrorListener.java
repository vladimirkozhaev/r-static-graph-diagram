package org.newlanguageservice.rlang;

import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;




public class GrammarErrorListener extends BaseErrorListener {
	private final Set<GrammarValidationError> mErrors = new HashSet<>();
	private String mMessage = null;
	private Parser mParser;
	private Lexer mLexer;
	public Set<GrammarValidationError> getErrors() {return mErrors;}


	public GrammarErrorListener(Parser pParser, Lexer pLexer)
	{
		this.mParser = pParser;
		this.mLexer = pLexer;

		mParser.removeErrorListeners();
		mParser.addErrorListener(this);
		mLexer.removeErrorListeners();
		mLexer.addErrorListener(this);
	}

	public GrammarErrorListener(Parser pParser, Lexer pLexer,String pMessage)
	{
		this(pParser, pLexer);
		mMessage = pMessage;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> pRecognizer, Object pOffendingSymbol, int pLine, int pCharPositionInLine,
			String pMsg, RecognitionException pE) {
		CustomInputStream inputStream = (CustomInputStream) mLexer.getInputStream();
		
		String text = inputStream.getInput();
		
		int offset = calculateOffset(pLine, pCharPositionInLine, text);
				
		mErrors.add(new GrammarValidationError(pLine, pCharPositionInLine, offset, mMessage == null ? pMsg : mMessage));
	}
	
	private int calculateOffset(int pLine, int pCharPositionInLine, String pText) {
		int line = 1;
		int offset = 0;
		String[] split = pText.split("");
		while (line < pLine && offset < pText.length() && offset < split.length) {
			if (split[offset].equals("\n") || split[offset].equals("\r")) {
				line++;
			}
			offset++;
		}

		
		return offset + pCharPositionInLine;
	}




}
