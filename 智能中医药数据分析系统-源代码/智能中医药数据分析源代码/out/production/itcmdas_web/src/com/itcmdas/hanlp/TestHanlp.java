package com.itcmdas.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.corpus.FileDataSet;
import com.hankcs.hanlp.classification.corpus.IDataSet;
//"E:\\GitCode\\NLPData\\ChnSentiCorp.zip"
import java.io.File;
import java.io.IOException;

import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.utility.TextUtility;
/**
 * @author TangBo
 * @description 测试类
 * @data 2020/11/18
 */
public class TestHanlp {
//    TextUtility textUtility=new TextUtility();
    public static final String CORPUS_FOLDER =TestTextUtility.ensureTestData("ChnSentiCorp情感分析酒店评论", "http://file.hankcs.com/corpus/ChnSentiCorp.zip");
    public static final String MODEL_PATH ="data/myModel";
    public static void main(String[] args) throws IOException {

        /**
         * 中文情感挖掘语料-ChnSentiCorp 谭松波
         */

//            IClassifier classifier = new NaiveBayesClassifier(); // 创建分类器，更高级的功能请参考IClassifier的接口定义
//            classifier.train(CORPUS_FOLDER);
//            NaiveBayesModel model =new NaiveBayesModel();
        NaiveBayesModel model = (NaiveBayesModel) IOUtil.readObjectFrom(MODEL_PATH);
        IClassifier classifier = new NaiveBayesClassifier(model);
//             model = (NaiveBayesModel) classifier.getModel();
//             IOUtil.saveObjectTo(model, MODEL_PATH);// 训练后的模型支持持久化，下次就不必训练了
//        IClassifier classifier = new NaiveBayesClassifier(model);
            predict(classifier, "不爱这个东西");
            predict(classifier, "结果大失所望，灯光昏暗，空间极其狭小，床垫质量恶劣，房间还伴着一股霉味。");
            predict(classifier, "可利用文本分类实现情感分析，效果还行");
        }

        private static void predict(IClassifier classifier, String text)
        {
            System.out.printf("《%s》 情感极性是 【%s】\n", text, classifier.classify(text));
        }

        static
        {
            File corpusFolder = new File(CORPUS_FOLDER);
            if (!corpusFolder.exists() || !corpusFolder.isDirectory())
            {
                System.err.println("没有文本分类语料，请阅读IClassifier.train(java.lang.String)中定义的语料格式、准备语料");
                System.exit(1);
            }
        }
}
