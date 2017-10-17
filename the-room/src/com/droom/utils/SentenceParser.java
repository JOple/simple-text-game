package com.droom.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.droom.SceneCommand;
import com.droom.grammar.DRoomGrammarLexer;
import com.droom.grammar.DRoomGrammarParser;
import com.droom.grammar.DRoomGrammarParser.ClauseContext;
import com.droom.grammar.DRoomGrammarParser.ObjectContext;
import com.droom.grammar.DRoomGrammarParser.TargetContext;
import com.droom.grammar.DRoomGrammarParser.VerbContext;

public class SentenceParser {

	public static List<SceneCommand> parse(String str) {
		if(str == null || str.length() == 0)
			return Collections.singletonList(Utils.cmdAct(null));

		DRoomGrammarLexer lexer = new DRoomGrammarLexer(CharStreams.fromString(str));
		TokenStream stream = new CommonTokenStream(lexer);
		DRoomGrammarParser parser = new DRoomGrammarParser(stream);

		List<SceneCommand> list = new ArrayList<>();
		for(ClauseContext clause : parser.statement().clause()) {
			List<String> raw = new ArrayList<>();

			for(TerminalNode conj : clause.CONJUNCTION())
				raw.add(conj.getText());

			String verb = null, target = null;

			if(clause.verb() != null) {
				VerbContext vctx = clause.verb();
				for(Method method : vctx.getClass().getDeclaredMethods()) {
					if(method.getParameterTypes().length > 0)
						continue;
					if(!TerminalNode.class.isAssignableFrom(method.getReturnType()))
						continue;
					try {
						TerminalNode v = (TerminalNode) method.invoke(clause.verb());
						if(v != null) {
							raw.add(v.getText());
							verb = method.getName().toLowerCase();
							break;
						}
					} catch (Exception e) {
						throw new RuntimeException("Error in invoking the method");
					}
				}
			}
			if(clause.target() != null && clause.target().object() != null) {
				TargetContext tctx = clause.target();
				if(tctx.PREPOSITION() != null)
					raw.add(tctx.PREPOSITION().getText());
				if(tctx.ARTICLE() != null)
					raw.add(tctx.ARTICLE().getText());
				for(TerminalNode adj : tctx.ADJECTIVE())
					raw.add(adj.getText());

				ObjectContext octx = tctx.object();
				for(Method method : octx.getClass().getDeclaredMethods()) {
					if(method.getParameterTypes().length > 0)
						continue;
					if(!TerminalNode.class.isAssignableFrom(method.getReturnType()))
						continue;
					try {
						TerminalNode t = (TerminalNode) method.invoke(octx);
						if(t != null) {
							raw.add(t.getText());
							if(method.getName().equals("IDENTIFIER"))
								target = t.getText();
							else
								target = method.getName().toLowerCase();
							break;
						}
					} catch (Exception e) {
						throw new RuntimeException("Error in invoking the method");
					}
				}
			}
			if(target == null)
				list.add(Utils.cmd(String.join(" ", raw), verb));
			else
				list.add(Utils.cmd(String.join(" ", raw), verb, target));
		}
		return Collections.unmodifiableList(list);
	}
}
