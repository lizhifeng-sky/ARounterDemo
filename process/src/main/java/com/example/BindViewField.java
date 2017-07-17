package com.example;

import java.util.HashMap;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class BindViewField {
    private VariableElement variableElement;
    private String url;

    public BindViewField(Element element) {
        if (element.getKind()!= ElementKind.METHOD){
            throw new IllegalArgumentException(
                    String.format("\"Only fields can be annotated with @%s\",\n" +
                    "                    BindView.class.getSimpleName())"));
        }
        variableElement = (VariableElement) element;
        Http http=variableElement.getAnnotation(Http.class);
        url=http.url();
        if (url.equals("")){
            throw new IllegalArgumentException(String.format("url() in %s for field %s is not valid !", Http.class.getSimpleName(),
                    variableElement.getSimpleName()));
        }
    }
    /**
     * 获取变量名称
     *
     * @return
     */
    Name getFieldName() {
        return variableElement.getSimpleName();
    }

    /**
     * 获取变量id
     *
     * @return
     */
    String getUrl() {
        return url;
    }

    /**
     * 获取变量类型
     *
     * @return
     */
    TypeMirror getFieldType() {
        return variableElement.asType();
    }
}
