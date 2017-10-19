package com.droom.grammar;

// Generated from DRoomGrammar.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class DRoomGrammarParser extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int CONJUNCTION = 1, PREPOSITION = 2, ARTICLE = 3, ADJECTIVE = 4, LOOK = 5, MOVE = 6,
			INSPECT = 7, TAKE = 8, INVENTORY = 9, INPUT = 10, COMBINE = 11, USE = 12, OPEN = 13, LEFT = 14, RIGHT = 15,
			FRONT = 16, BACK = 17, UP = 18, DOWN = 19, BED = 20, DRAWER = 21, CLOCK = 22, DOOR = 23, PAINTING = 24,
			CHEST = 25, SHELF = 26, WINDOW = 27, BLADE = 28, HANDLE = 29, SAW = 30, IDENTIFIER = 31, WS = 32;
	public static final int RULE_statement = 0, RULE_clause = 1, RULE_verb = 2, RULE_target = 3, RULE_object = 4;
	public static final String[] ruleNames = { "statement", "clause", "verb", "target", "object" };

	private static final String[] _LITERAL_NAMES = {};
	private static final String[] _SYMBOLIC_NAMES = { null, "CONJUNCTION", "PREPOSITION", "ARTICLE", "ADJECTIVE",
			"LOOK", "MOVE", "INSPECT", "TAKE", "INVENTORY", "INPUT", "COMBINE", "USE", "OPEN", "LEFT", "RIGHT", "FRONT",
			"BACK", "UP", "DOWN", "BED", "DRAWER", "CLOCK", "DOOR", "PAINTING", "CHEST", "SHELF", "WINDOW", "BLADE",
			"HANDLE", "SAW", "IDENTIFIER", "WS" };
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() {
		return "DRoomGrammar.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public DRoomGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	public static class StatementContext extends ParserRuleContext {
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}

		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class, i);
		}

		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_statement;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DRoomGrammarListener)
				((DRoomGrammarListener) listener).enterStatement(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DRoomGrammarListener)
				((DRoomGrammarListener) listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(11);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(10);
							clause();
						}
					}
					setState(13);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONJUNCTION) | (1L << PREPOSITION)
						| (1L << ARTICLE) | (1L << ADJECTIVE) | (1L << LOOK) | (1L << MOVE) | (1L << INSPECT)
						| (1L << TAKE) | (1L << INVENTORY) | (1L << INPUT) | (1L << COMBINE) | (1L << USE)
						| (1L << OPEN) | (1L << LEFT) | (1L << RIGHT) | (1L << FRONT) | (1L << BACK) | (1L << UP)
						| (1L << DOWN) | (1L << BED) | (1L << DRAWER) | (1L << CLOCK) | (1L << DOOR) | (1L << PAINTING)
						| (1L << CHEST) | (1L << SHELF) | (1L << WINDOW) | (1L << BLADE) | (1L << HANDLE) | (1L << SAW)
						| (1L << IDENTIFIER))) != 0));
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClauseContext extends ParserRuleContext {
		public TargetContext target() {
			return getRuleContext(TargetContext.class, 0);
		}

		public List<TerminalNode> CONJUNCTION() {
			return getTokens(DRoomGrammarParser.CONJUNCTION);
		}

		public TerminalNode CONJUNCTION(int i) {
			return getToken(DRoomGrammarParser.CONJUNCTION, i);
		}

		public VerbContext verb() {
			return getRuleContext(VerbContext.class, 0);
		}

		public ClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_clause;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DRoomGrammarListener)
				((DRoomGrammarListener) listener).enterClause(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DRoomGrammarListener)
				((DRoomGrammarListener) listener).exitClause(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		ClauseContext _localctx = new ClauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clause);
		int _la;
		try {
			setState(26);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 3, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(18);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == CONJUNCTION) {
					{
						{
							setState(15);
							match(CONJUNCTION);
						}
					}
					setState(20);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la)
						& ((1L << LOOK) | (1L << MOVE) | (1L << INSPECT) | (1L << TAKE) | (1L << INVENTORY)
								| (1L << INPUT) | (1L << COMBINE) | (1L << USE) | (1L << OPEN))) != 0)) {
					{
						setState(21);
						verb();
					}
				}

				setState(24);
				target();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(25);
				verb();
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VerbContext extends ParserRuleContext {
		public TerminalNode COMBINE() {
			return getToken(DRoomGrammarParser.COMBINE, 0);
		}

		public TerminalNode INPUT() {
			return getToken(DRoomGrammarParser.INPUT, 0);
		}

		public TerminalNode INSPECT() {
			return getToken(DRoomGrammarParser.INSPECT, 0);
		}

		public TerminalNode INVENTORY() {
			return getToken(DRoomGrammarParser.INVENTORY, 0);
		}

		public TerminalNode LOOK() {
			return getToken(DRoomGrammarParser.LOOK, 0);
		}

		public TerminalNode MOVE() {
			return getToken(DRoomGrammarParser.MOVE, 0);
		}

		public TerminalNode OPEN() {
			return getToken(DRoomGrammarParser.OPEN, 0);
		}

		public TerminalNode TAKE() {
			return getToken(DRoomGrammarParser.TAKE, 0);
		}

		public TerminalNode USE() {
			return getToken(DRoomGrammarParser.USE, 0);
		}

		public VerbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_verb;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DRoomGrammarListener)
				((DRoomGrammarListener) listener).enterVerb(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DRoomGrammarListener)
				((DRoomGrammarListener) listener).exitVerb(this);
		}
	}

	public final VerbContext verb() throws RecognitionException {
		VerbContext _localctx = new VerbContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_verb);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(28);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la)
						& ((1L << LOOK) | (1L << MOVE) | (1L << INSPECT) | (1L << TAKE) | (1L << INVENTORY)
								| (1L << INPUT) | (1L << COMBINE) | (1L << USE) | (1L << OPEN))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF)
						matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TargetContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class, 0);
		}

		public TerminalNode PREPOSITION() {
			return getToken(DRoomGrammarParser.PREPOSITION, 0);
		}

		public TerminalNode ARTICLE() {
			return getToken(DRoomGrammarParser.ARTICLE, 0);
		}

		public List<TerminalNode> ADJECTIVE() {
			return getTokens(DRoomGrammarParser.ADJECTIVE);
		}

		public TerminalNode ADJECTIVE(int i) {
			return getToken(DRoomGrammarParser.ADJECTIVE, i);
		}

		public TargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_target;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DRoomGrammarListener)
				((DRoomGrammarListener) listener).enterTarget(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DRoomGrammarListener)
				((DRoomGrammarListener) listener).exitTarget(this);
		}
	}

	public final TargetContext target() throws RecognitionException {
		TargetContext _localctx = new TargetContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_target);
		int _la;
		try {
			setState(46);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 7, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == PREPOSITION) {
					{
						setState(30);
						match(PREPOSITION);
					}
				}

				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == ARTICLE) {
					{
						setState(33);
						match(ARTICLE);
					}
				}

				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == ADJECTIVE) {
					{
						{
							setState(36);
							match(ADJECTIVE);
						}
					}
					setState(41);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(42);
				object();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(43);
				match(PREPOSITION);
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(44);
				match(ARTICLE);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(45);
				match(ADJECTIVE);
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectContext extends ParserRuleContext {
		public TerminalNode BACK() {
			return getToken(DRoomGrammarParser.BACK, 0);
		}

		public TerminalNode BED() {
			return getToken(DRoomGrammarParser.BED, 0);
		}

		public TerminalNode BLADE() {
			return getToken(DRoomGrammarParser.BLADE, 0);
		}

		public TerminalNode CHEST() {
			return getToken(DRoomGrammarParser.CHEST, 0);
		}

		public TerminalNode CLOCK() {
			return getToken(DRoomGrammarParser.CLOCK, 0);
		}

		public TerminalNode DOOR() {
			return getToken(DRoomGrammarParser.DOOR, 0);
		}

		public TerminalNode DOWN() {
			return getToken(DRoomGrammarParser.DOWN, 0);
		}

		public TerminalNode FRONT() {
			return getToken(DRoomGrammarParser.FRONT, 0);
		}

		public TerminalNode HANDLE() {
			return getToken(DRoomGrammarParser.HANDLE, 0);
		}

		public TerminalNode DRAWER() {
			return getToken(DRoomGrammarParser.DRAWER, 0);
		}

		public TerminalNode LEFT() {
			return getToken(DRoomGrammarParser.LEFT, 0);
		}

		public TerminalNode PAINTING() {
			return getToken(DRoomGrammarParser.PAINTING, 0);
		}

		public TerminalNode RIGHT() {
			return getToken(DRoomGrammarParser.RIGHT, 0);
		}

		public TerminalNode SAW() {
			return getToken(DRoomGrammarParser.SAW, 0);
		}

		public TerminalNode SHELF() {
			return getToken(DRoomGrammarParser.SHELF, 0);
		}

		public TerminalNode UP() {
			return getToken(DRoomGrammarParser.UP, 0);
		}

		public TerminalNode WINDOW() {
			return getToken(DRoomGrammarParser.WINDOW, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(DRoomGrammarParser.IDENTIFIER, 0);
		}

		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DRoomGrammarListener)
				((DRoomGrammarListener) listener).enterObject(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DRoomGrammarListener)
				((DRoomGrammarListener) listener).exitObject(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(48);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFT) | (1L << RIGHT) | (1L << FRONT)
						| (1L << BACK) | (1L << UP) | (1L << DOWN) | (1L << BED) | (1L << DRAWER) | (1L << CLOCK)
						| (1L << DOOR) | (1L << PAINTING) | (1L << CHEST) | (1L << SHELF) | (1L << WINDOW)
						| (1L << BLADE) | (1L << HANDLE) | (1L << SAW) | (1L << IDENTIFIER))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF)
						matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN = "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"\65\4\2\t\2\4\3"
			+ "\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\6\2\16\n\2\r\2\16\2\17\3\3\7\3\23\n\3"
			+ "\f\3\16\3\26\13\3\3\3\5\3\31\n\3\3\3\3\3\5\3\35\n\3\3\4\3\4\3\5\5\5\""
			+ "\n\5\3\5\5\5%\n\5\3\5\7\5(\n\5\f\5\16\5+\13\5\3\5\3\5\3\5\3\5\5\5\61\n"
			+ "\5\3\6\3\6\3\6\2\2\7\2\4\6\b\n\2\4\3\2\7\17\3\2\20!\29\2\r\3\2\2\2\4\34"
			+ "\3\2\2\2\6\36\3\2\2\2\b\60\3\2\2\2\n\62\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2"
			+ "\2\16\17\3\2\2\2\17\r\3\2\2\2\17\20\3\2\2\2\20\3\3\2\2\2\21\23\7\3\2\2"
			+ "\22\21\3\2\2\2\23\26\3\2\2\2\24\22\3\2\2\2\24\25\3\2\2\2\25\30\3\2\2\2"
			+ "\26\24\3\2\2\2\27\31\5\6\4\2\30\27\3\2\2\2\30\31\3\2\2\2\31\32\3\2\2\2"
			+ "\32\35\5\b\5\2\33\35\5\6\4\2\34\24\3\2\2\2\34\33\3\2\2\2\35\5\3\2\2\2"
			+ "\36\37\t\2\2\2\37\7\3\2\2\2 \"\7\4\2\2! \3\2\2\2!\"\3\2\2\2\"$\3\2\2\2"
			+ "#%\7\5\2\2$#\3\2\2\2$%\3\2\2\2%)\3\2\2\2&(\7\6\2\2\'&\3\2\2\2(+\3\2\2"
			+ "\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3\2\2\2,\61\5\n\6\2-\61\7\4\2\2.\61"
			+ "\7\5\2\2/\61\7\6\2\2\60!\3\2\2\2\60-\3\2\2\2\60.\3\2\2\2\60/\3\2\2\2\61"
			+ "\t\3\2\2\2\62\63\t\3\2\2\63\13\3\2\2\2\n\17\24\30\34!$)\60";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}