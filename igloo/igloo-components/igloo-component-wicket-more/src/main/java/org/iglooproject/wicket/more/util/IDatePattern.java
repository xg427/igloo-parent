package org.iglooproject.wicket.more.util;

/**
 * @deprecated Use new API date from java.time.
 */
@Deprecated
public interface IDatePattern {
	
	String getJavaPatternKey();

	String getJavascriptPatternKey();
	
	boolean capitalize();

}
