package org.newlanguageservice.rlang;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

import antlr.RLangBaseListener;
import antlr.RLangLexer;
import antlr.RLangListener;
import antlr.RLangParser;

public class RLangValidationTest {
	public static Set<GrammarValidationError> validate(String validateString) {

		RLangLexer lexer = new RLangLexer(new CustomInputStream(validateString));

		// Get a list of matched tokens
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// Pass the tokens to the parser
		RLangParser parser = new RLangParser(tokens);
		parser.getInterpreter().setPredictionMode(PredictionMode.LL);

		// Specify our entry point

		// Walk it and attach our listener
		ParseTreeWalker walker = new ParseTreeWalker();

		GrammarErrorListener listener = new GrammarErrorListener(parser, lexer);

		RLangListener base = new RLangBaseListener();
		ParserRuleContext drinkSentenceContext = parser.lang();
		walker.walk(base, drinkSentenceContext);

		return listener.getErrors();
	}

	@Test
	public void testSimpleText() {
		assertEquals(0, validate("a extends b,c,d;").size());
	}
}
