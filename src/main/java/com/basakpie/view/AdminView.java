package com.basakpie.view;

import com.basakpie.security.User;
import com.basakpie.security.UserRepository;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by basakpie on 2017. 5. 11..
 */
@Secured({"ROLE_ADMIN"})
@SpringView(name = AdminView.VIEW_NAME)
public class AdminView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "admin";

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init() {
        addComponent(new Label("Hello, this is admin view."));

        List<User> users = userRepository.findAll();

        Grid<User> grid = new Grid<>();
        grid.setSizeFull();
        grid.setItems(users);
        grid.addColumn(User::getUsername).setCaption("Name");
        grid.addColumn(User::getPassword).setCaption("Password");

        addComponent(grid);
        setExpandRatio(grid, 1f);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}

