package com.gson.lucene.analzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.miscellaneous.LengthFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;

public class PartOffSpeechAnalyzer extends Analyzer {
    private Version matchVersion;

    public PartOffSpeechAnalyzer(Version matchVersion) {
        this.matchVersion = matchVersion;
    }

    /**
     * 装饰者模式
     * @param fieldName
     * @return
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source = new WhitespaceTokenizer();
        TokenStream result = new LengthFilter(source, 3, Integer.MAX_VALUE);
        result = new PartOfSpeechTaggingFilter(result);
        return new TokenStreamComponents(source, result);
    }

    public static void main(String[] args) throws IOException {
        // text to tokenize
        final String text = "This is a demo of the TokenStream API";
        Version matchVersion = Version.LUCENE_8_0_0; // Substitute desired Lucene version for XY

        PartOffSpeechAnalyzer analyzer = new PartOffSpeechAnalyzer(matchVersion);
        TokenStream stream = analyzer.tokenStream("field", new StringReader(text));

        // get the CharTermAttribute from the TokenStream
        CharTermAttribute termAtt = stream.addAttribute(CharTermAttribute.class);

        // get the PartOfSpeechAttribute from the TokenStream
        PartOfSpeechAttribute posAtt = stream.addAttribute(PartOfSpeechAttribute.class);

        try {
            stream.reset();

            // print all tokens until stream is exhausted
            while (stream.incrementToken()) {
                System.out.println(termAtt.toString() + ": " + posAtt.getPartOfSpeech());
            }

            stream.end();
        } finally {
            stream.close();
        }
    }
}
