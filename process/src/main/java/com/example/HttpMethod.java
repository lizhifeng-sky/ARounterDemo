package com.example;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

/**
 * Created by Administrator on 2017/7/18 0018.
 */
public class HttpMethod {
    private ExecutableElement executableElement;
    private String url;
    private HttpType httpType;

    public HttpMethod(Element executableElement) {
        if (executableElement.getKind() != ElementKind.METHOD) {
            throw new IllegalArgumentException("only accpet method");
        }
        this.executableElement = (ExecutableElement) executableElement;
        Http http = executableElement.getAnnotation(Http.class);
        url = http.url();
        httpType = http.type();
        if (url == null || url.equals("")) {
            throw new IllegalArgumentException("url cannot be null");
        }
        if (httpType == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        getName();
        getParameters();
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        System.out.println("name" + "" + executableElement.getSimpleName());
        return executableElement.getSimpleName().toString();
    }

    public List<? extends VariableElement> getParameters() {
        System.out.println("lzf_httpMethod" + "" + executableElement.getParameters().toString());
        return executableElement.getParameters();
    }

}
