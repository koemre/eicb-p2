/*******************************************************************************
 * Copyright (c) 2016-2019 Embedded Systems and Applications Group
 * Department of Computer Science, Technische Universitaet Darmstadt,
 * Hochschulstr. 10, 64289 Darmstadt, Germany.
 *
 * All rights reserved.
 *
 * This software is provided free for educational use only.
 * It may not be used for commercial purposes without the
 * prior written permission of the authors.
 ******************************************************************************/
package mavlc.services.visualization;

import mavlc.syntax.AstNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HtmlDumper extends Dumper {
	protected static final Map<AppendType, String> cssClasses;
	
	static {
		Map<AppendType, String> classes = new HashMap<>();
		classes.put(AppendType.type, "t");
		classes.put(AppendType.string, "s");
		classes.put(AppendType.number, "n");
		classes.put(AppendType.keyword, "k");
		//classes.put(AppendType.operator, "o");
		classes.put(AppendType.recordName, "r");
		//classes.put(AppendType.whitespace, "w");
		//classes.put(AppendType.identifier, "i");
		//classes.put(AppendType.punctuation, "p");
		classes.put(AppendType.functionName, "f");
		classes.put(AppendType.recordElementName, "e");
		cssClasses = Collections.unmodifiableMap(classes);
	}
	
	public static String dump(AstNode node) {
		HtmlDumper visitor = new HtmlDumper();
		visitor.addPrologue();
		visitor.visit(node);
		visitor.addEpilogue();
		return visitor.sb.toString();
	}
	
	protected String lastClass;
	
	@Override
	protected void append(Object source, AppendType type) {
		String nextClass = cssClasses.get(type);
		if(!Objects.equals(lastClass, nextClass)) {
			if(lastClass != null) {
				sb.append("</span>");
			}
			if(nextClass != null) {
				sb.append("<span class=\"");
				sb.append(nextClass);
				sb.append("\">");
			}
		}
		sb.append(source);
		lastClass = nextClass;
	}
	
	protected void addPrologue() {
		sb.append("<html>\n<head>\n<style>\n" +
				".k { color: #0000ff; }\n" +
				".t { color: #ff7f00; }\n" +
				".n { color: #E09920; }\n" +
				".i { color: #000000; }\n" +
				".p { color: #000000; }\n" +
				".r { color: #ff7f00; }\n" +
				".e { color: #2b91af; }\n" +
				".f { color: #2b91af; }\n" +
				".o { color: #000000; }\n" +
				".s { color: #a31515; }\n" +
				".w { }\n" +
				"</style>\n</head>\n<body>\n<pre>");
	}
	
	protected void addEpilogue() {
		if(lastClass != null) sb.append("</span>");
		sb.append("\n</pre>\n</body>\n</html>");
	}
}
