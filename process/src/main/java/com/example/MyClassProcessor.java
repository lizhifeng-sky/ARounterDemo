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
public class MyClassProcessor extends AbstractProcessor {
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
        processBindView(roundEnv);
        for (AnnotationUtils annotationUtils:annotationUtilsMap.values()) {
            try {
                annotationUtils.generateFile().writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
                error("Generate file failed, reason: %s", e.getMessage());
            }
        }
       return true;
    }
    private void processBindView(RoundEnvironment environment){
        for (Element e : environment.getElementsAnnotatedWith(Http.class)) {
            AnnotationUtils annotationUtils=getAnnotationUtils(e);
            BindViewField bindViewField=new BindViewField(e);
            annotationUtils.addField(bindViewField);
        }
    }
    private AnnotationUtils getAnnotationUtils(Element e){
        TypeElement typeElement= (TypeElement) e.getEnclosingElement();
        String fullName=typeElement.getQualifiedName().toString();
        AnnotationUtils annotationUtils=annotationUtilsMap.get(fullName);
        if (annotationUtils==null){
            annotationUtils=new AnnotationUtils(typeElement,elements);
            annotationUtilsMap.put(fullName,annotationUtils);
        }
        return annotationUtils;
    }
    private void error(String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types=new LinkedHashSet<>();
        types.add(Http.class.getCanonicalName());
        return types;
    }
}
