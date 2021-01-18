package com.itcmdas.hanlp;

import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;

import java.io.File;

/**
 * @author TangBo
 * @description hanlp情感分析实现类
 * @data 2020/11/18
 */
//predict(classifier, "不爱这个东西");
public class HanlpService {

//    public static final String CORPUS_FOLDER =TestTextUtility.ensureTestData("ChnSentiCorp情感分析酒店评论", "http://file.hankcs.com/corpus/ChnSentiCorp.zip");
    public static String MODEL_PATH ="myModel";
    public static NaiveBayesModel  hanlpModel(String path){
        NaiveBayesModel model = (NaiveBayesModel) IOUtil.readObjectFrom(path);
        MODEL_PATH=path;
        if(model==null)
            System.out.println("kokoko");
//        IClassifier classifier = new NaiveBayesClassifier(model);
        return model ;
    }

    public static String predict(IClassifier classifier, String text)
    {
//        System.out.println(classifier.predict(text));
//        System.out.println(classifier.classify(text));
        return classifier.classify(text);
    }

//    static
//    {
//        File corpusFolder = new File(MODEL_PATH);
//        if (!corpusFolder.exists() || !corpusFolder.isDirectory())
//        {
//            System.err.println("没有文本分类语料，请阅读IClassifier.train(java.lang.String)中定义的语料格式、准备语料");
//            System.exit(1);
//        }
//    }
}
