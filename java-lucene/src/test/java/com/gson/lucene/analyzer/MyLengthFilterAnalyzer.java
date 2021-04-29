package com.gson.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.miscellaneous.LengthFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;

/**
 * https://lucene.apache.org/core/8_8_2/core/org/apache/lucene/analysis/package-summary.html#analysis-workflow
 * Adding a LengthFilter
 */
public class MyLengthFilterAnalyzer extends Analyzer {
    private Version matchVersion;

    public MyLengthFilterAnalyzer(Version matchVersion) {
        this.matchVersion = matchVersion;
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        WhitespaceTokenizer source = new WhitespaceTokenizer();
        LengthFilter result = new LengthFilter(source, 3, Integer.MAX_VALUE);
        return new TokenStreamComponents(source, result);
    }

    public static void main(String[] args) throws IOException {
        // text to tokenize
        final String text = "This is a demo of the TokenStream API";

        Version matchVersion = Version.LUCENE_8_8_2; // Substitute desired Lucene version for XY
        MyLengthFilterAnalyzer analyzer = new MyLengthFilterAnalyzer(matchVersion);
        TokenStream stream = analyzer.tokenStream("field", new StringReader(text));

        // get the CharTermAttribute from the TokenStream
        CharTermAttribute termAtt = stream.addAttribute(CharTermAttribute.class);

        try {
            stream.reset();

            // print all tokens until stream is exhausted
            while (stream.incrementToken()) {
                System.out.println(termAtt.toString());
            }

            stream.end();
        } finally {
            stream.close();
        }
    }
}
