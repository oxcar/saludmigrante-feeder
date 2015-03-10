package com.copili.feeder.util;

import java.util.HashMap;
import java.util.Map;

public class HtmlFormatter {

	private static final Map<String,String> htmlCodes = new HashMap<>();

	static {
		htmlCodes.put( "[negrita]", "<strong>" );
		htmlCodes.put( "[/negrita]", "</strong>" );
		htmlCodes.put( "[italica]", "<em>" );
		htmlCodes.put( "[/italica]", "</em>" );
		htmlCodes.put( "[color-rojo]", "<span style=\"color:#E74C3C\">" );
		htmlCodes.put( "[/color-rojo]", "</span>" );
		htmlCodes.put( "[color-azul]", "<span style=\"color:#3498DB\">" );
		htmlCodes.put( "[/color-azul]", "</span>" );
		htmlCodes.put( "[color-verde]", "<span style=\"color:#2ECC71\">" );
		htmlCodes.put( "[/color-verde]", "</span>" );
		// Versiones abreviadas
		htmlCodes.put( "[b]", "<strong>" );
		htmlCodes.put( "[/b]", "</strong>" );
		htmlCodes.put( "[i]", "<em>" );
		htmlCodes.put( "[/i]", "</em>" );
		htmlCodes.put( "[cr]", "<span style=\"color:#E74C3C\">" );
		htmlCodes.put( "[/cr]", "</span>" );
		htmlCodes.put( "[ca]", "<span style=\"color:#3498DB\">" );
		htmlCodes.put( "[/ca]", "</span>" );
		htmlCodes.put( "[cv]", "<span style=\"color:#2ECC71\">" );
		htmlCodes.put( "[/cv]", "</span>" );
	}

	public static String formatHtml( String input ) {
		String tmp = input;
		for( String key : htmlCodes.keySet()) {
			tmp = tmp.replace( key, htmlCodes.get( key ) );
		}
		return tmp;
	}

	public static String stripFormat( String input ) {
		String tmp = input;
		for( String key : htmlCodes.keySet()) {
			tmp = tmp.replace( key, "" );
		}
		return tmp;
	}

}
