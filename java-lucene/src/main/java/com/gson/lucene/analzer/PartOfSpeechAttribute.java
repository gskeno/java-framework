package com.gson.lucene.analzer;

import org.apache.lucene.util.Attribute;

public interface PartOfSpeechAttribute extends Attribute {
    public static enum PartOfSpeech {
        /**
         * 名词
         */
        Noun,
        /**
         * 动词
         */
        Verb,
        /**
         * 形容词
         */
        Adjective,
        /**
         * 副词
         */
        Adverb,
        /**
         * 代词
         */
        Pronoun,
        /**
         * 介词
         */
        Preposition,
        /**
         * 连词
         */
        Conjunction,
        /**
         * 冠词
         */
        Article,
        Unknown
    }

    public void setPartOfSpeech(PartOfSpeech pos);

    public PartOfSpeech getPartOfSpeech();
}
