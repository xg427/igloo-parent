package org.iglooproject.wicket.more.date.pattern;

public interface IDatePattern {
	
	String getJavaPatternKey();

	String getJavascriptPatternKey();
	
	boolean capitalize();

}
