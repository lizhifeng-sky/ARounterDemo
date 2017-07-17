package com.example;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class AnnotationUtils {
    private static class TypeUtil{
        static final ClassName BINDER=ClassName.get("com.example.myapi","HttpBinder");
        static final ClassName FINDER=ClassName.get("com.example.myapi","HttpFinder");
    }
    private TypeElement typeElement;
    private ArrayList<BindViewField> mFields;
    private Elements elements;

    public AnnotationUtils(TypeElement typeElement, Elements elements) {
        this.typeElement = typeElement;
        this.elements = elements;
        mFields=new ArrayList<>();
    }
    void addField(BindViewField field){
        mFields.add(field);
    }
    JavaFile generateFile(){
        MethodSpec.Builder bindViewMethod = MethodSpec.methodBuilder("doHttp")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(typeElement.asType()), "activity")
                .addParameter(TypeName.OBJECT, "source")
                .addParameter(TypeUtil.FINDER, "finder");

        for (BindViewField field : mFields) {
            // find views
            bindViewMethod.addStatement("host.$N = ($T)(finder.findView(source, $L))", field.getFieldName(), ClassName.get(field.getFieldType()), field.getUrl());
        }

        MethodSpec.Builder unBindViewMethod = MethodSpec.methodBuilder("unBindView")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeName.get(typeElement.asType()), "host")
                .addAnnotation(Override.class);
        for (BindViewField field : mFields) {
            unBindViewMethod.addStatement("host.$N = null", field.getFieldName());
        }

        //generaClass
        TypeSpec injectClass = TypeSpec.classBuilder(typeElement.getSimpleName() + "$$Http")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeUtil.BINDER, TypeName.get(typeElement.asType())))
                .addMethod(bindViewMethod.build())
                .addMethod(unBindViewMethod.build())
                .build();

        String packageName = elements.getPackageOf(typeElement).getQualifiedName().toString();

        return JavaFile.builder(packageName, injectClass).build();
    }
}
