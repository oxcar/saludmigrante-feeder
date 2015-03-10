package com.copili.feeder.util;

import java.text.Normalizer;

public class StringUtils {

    public static String normalize( String s ) {
        if( org.apache.commons.lang.StringUtils.isBlank( s ) ) {
            return s;
        }
        String normalized = Normalizer.normalize( s, Normalizer.Form.NFD );
        return normalized.replaceAll( "\\p{M}", "" );
    }

}
