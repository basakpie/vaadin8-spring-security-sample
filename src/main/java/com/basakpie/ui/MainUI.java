package com.basakpie.ui;

import com.basakpie.security.SecurityContextUtils;
import com.basakpie.view.AccessDeniedView;
import com.basakpie.view.AdminView;
import com.basakpie.view.ErrorView;
import com.basakpie.view.UserView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.security.VaadinSecurity;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * Created by basakpie on 2017. 5. 11..
 */
@Theme("Demo")
@SpringUI(path = "/")
@SpringViewDisplay
public class MainUI extends UI implements ViewDisplay {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	VaadinSecurity vaadinSecurity;

	@Autowired
	SpringViewProvider springViewProvider;

	@Autowired
	SpringNavigator springNavigator;

	Panel springViewDisplay;

	@PostConstruct
	public void init() {
		springNavigator.setErrorView(ErrorView.class);
		springViewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
		springViewDisplay = new Panel();
		springViewDisplay.setSizeFull();
	}

	@Override
	protected void init(VaadinRequest request){
		getPage().setTitle("Vaadin Security Demo");

		final CssLayout navigationBar = new CssLayout();
		navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		navigationBar.addComponent(createNavigationButton("User View", UserView.VIEW_NAME));
		navigationBar.addComponent(createNavigationButton("Admin View", AdminView.VIEW_NAME));
		navigationBar.addComponent(new Button("Logout", e -> getPage().setLocation("logout")));

		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		root.addComponents(new Label(SecurityContextUtils.getUser().getUsername() + " : " + LocalDateTime.now()));
		root.addComponent(navigationBar);
		root.addComponent(springViewDisplay);
		root.setExpandRatio(springViewDisplay, 1.0f);

		setContent(root);
	}

	@Override
	public void showView(View view) {
		springViewDisplay.setContent((Component)view);
	}

	private Button createNavigationButton(String caption, final String viewName) {
		Button button = new Button(caption);
		button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
		return button;
	}

}
