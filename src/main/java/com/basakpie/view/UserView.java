package com.basakpie.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by basakpie on 2017. 5. 11..
 */
@SpringView(name = UserView.VIEW_NAME)
public class UserView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";

    @PostConstruct
    public void init() {
        addComponent(new Label("Hello, this is user view."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
