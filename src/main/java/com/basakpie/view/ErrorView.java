package com.basakpie.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

/**
 * Created by basakpie on 2017. 5. 11..
 */
@SpringComponent
@UIScope
@SpringView
public class ErrorView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @PostConstruct
    void init() {
        Label label = new Label("Hello, this is the 'error view' loaded if no view is matched based on URL.");
        label.addStyleName(ValoTheme.LABEL_FAILURE);
        addComponent(label);
    }
}
