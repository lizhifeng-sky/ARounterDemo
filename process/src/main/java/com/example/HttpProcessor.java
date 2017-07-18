package com.example;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class HttpProcessor extends AbstractProcessor{
    private Filer filer;
    private Elements elements;
    private Messager messager;
    private Map<String,AnnotationUtils> annotationUtilsMap;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer=processingEnv.getFiler();
        elements=processingEnv.getElementUtils();
        annotationUtilsMap=new TreeMap<>();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        annotationUtilsMap.clear();
        processBinderHttp(roundEnv);
        for (AnnotationUtils a : annotationUtilsMap.values()) {
            try {
                a.generateFile().writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void processBinderHttp(RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getElementsAnnotatedWith(Http.class)) {
            AnnotationUtils a=getAnnotationUtils(e);
            HttpMethod httpMethod=new HttpMethod(e);
//            messager.printMessage(Diagnostic.Kind.ERROR,httpMethod.getName()+"  "+httpMethod.getParameters().toString());
            a.addMethod(httpMethod);
        }
    }

    private AnnotationUtils getAnnotationUtils(Element e) {
        TypeElement typeElement= (TypeElement) e.getEnclosingElement();
        String fullName=typeElement.getQualifiedName().toString();
        AnnotationUtils annotationUtils=annotationUtilsMap.get(fullName);
        if (annotationUtils==null){
            annotationUtils=new AnnotationUtils(typeElement,elements);
            annotationUtilsMap.put(fullName,annotationUtils);
        }
        return annotationUtils;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> type=new LinkedHashSet<>();
        type.add(Http.class.getCanonicalName());
        return type;
    }
}
