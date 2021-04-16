package com.example.provider.entity.annotation;

/**
 * json视图，jackson专用，fastjson一边去
 *
 * @author wakening
 * @see com.fasterxml.jackson.annotation.JsonView
 */
public class View {

    /**
     * 通用视图
     */
    public interface Public {
    }

    /**
     * 唯品富邦json视图，包含通用视图
     */
    public interface Self extends Public {
    }

}