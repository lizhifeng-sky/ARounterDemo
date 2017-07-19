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
    private static class TypeUtil {
        static final ClassName BINDER = ClassName.get("lzf.api", "HttpBinder");
        static final ClassName FINDER = ClassName.get("lzf.api", "HttpFinder");
    }

    private TypeElement typeElement;
    private ArrayList<HttpMethod> httpMethods;
    private Elements elements;

    public AnnotationUtils(TypeElement typeElement, Elements elements) {
        this.typeElement = typeElement;
        this.elements = elements;
        httpMethods = new ArrayList<>();
    }

    void addMethod(HttpMethod method) {
        httpMethods.add(method);
    }

    JavaFile generateFile() {
        MethodSpec.Builder bindHttpMethod = MethodSpec.methodBuilder("binder")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(typeElement.asType()), "activity")
                .addParameter(TypeUtil.FINDER, "finder");
        for (HttpMethod httpMethod : httpMethods) {
            // executeHttp
            bindHttpMethod.addStatement("executeHttp(activity, \"$L\")", httpMethod.getUrl());
//            bindHttpMethod.addStatement("host.$N = ($T)(finder.findView(source, $L))", field.getFieldName(), ClassName.get(field.getFieldType()), field.getResId());
        }
        MethodSpec.Builder bindHttpRequest = MethodSpec.methodBuilder("executeHttp")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(typeElement.getSuperclass()), "activity")
                .addParameter(String.class, "url");
        bindHttpRequest.addStatement("" +
                "mActivity=($L)activity;\n" +
                "mUrl=url;\n" +
                "new Thread(new Runnable() {\n" +
                "            @Override\n" +
                "            public void run() {\n" +
                "                final String response = $T.post(mUrl,\"2\");\n" +
                "                mActivity.test(response) ; " +
                "\n" +
                "            }\n" +
                "        }).start();",TypeName.get(typeElement.asType()),NetUtils.class);
        MethodSpec.Builder unBindViewMethod = MethodSpec.methodBuilder("unBinder")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeName.get(typeElement.asType()), "activity")
                .addAnnotation(Override.class);
//        for (HttpMethod field : mFields) {
//            unBindViewMethod.addStatement("host.$N = null", field.getFieldName());
//        }

        //generaClass
        TypeSpec injectClass = TypeSpec.classBuilder(typeElement.getSimpleName() + "$$Http")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeUtil.BINDER, TypeName.get(typeElement.asType())))
                .addSuperinterface(TypeUtil.FINDER)
                .addMethod(bindHttpMethod.build())
                .addField(TypeName.get(typeElement.asType()),"mActivity",Modifier.PRIVATE)
                .addField(String.class,"mUrl",Modifier.PRIVATE)
                .addMethod(unBindViewMethod.build())
                .addMethod(bindHttpRequest.build())
                .build();

        String packageName = elements.getPackageOf(typeElement).getQualifiedName().toString();

        return JavaFile.builder(packageName, injectClass).build();
    }
}
