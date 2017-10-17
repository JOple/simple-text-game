// Generated from DRoomGrammar.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DRoomGrammarParser}.
 */
public interface DRoomGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DRoomGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(DRoomGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DRoomGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(DRoomGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DRoomGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterClause(DRoomGrammarParser.ClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DRoomGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitClause(DRoomGrammarParser.ClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link DRoomGrammarParser#verb}.
	 * @param ctx the parse tree
	 */
	void enterVerb(DRoomGrammarParser.VerbContext ctx);
	/**
	 * Exit a parse tree produced by {@link DRoomGrammarParser#verb}.
	 * @param ctx the parse tree
	 */
	void exitVerb(DRoomGrammarParser.VerbContext ctx);
	/**
	 * Enter a parse tree produced by {@link DRoomGrammarParser#target}.
	 * @param ctx the parse tree
	 */
	void enterTarget(DRoomGrammarParser.TargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link DRoomGrammarParser#target}.
	 * @param ctx the parse tree
	 */
	void exitTarget(DRoomGrammarParser.TargetContext ctx);
	/**
	 * Enter a parse tree produced by {@link DRoomGrammarParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(DRoomGrammarParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link DRoomGrammarParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(DRoomGrammarParser.ObjectContext ctx);
}