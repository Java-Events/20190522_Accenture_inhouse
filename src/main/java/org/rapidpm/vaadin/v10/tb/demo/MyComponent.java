package org.rapidpm.vaadin.v10.tb.demo;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;


public class MyComponent
    extends Composite<VerticalLayout> {

  private final Button        myButton = new Button();
  private final TextField     username = new TextField();
  private final PasswordField password = new PasswordField();

  private PasswordService passwordService;

  public void usePasswordService(PasswordService passwordService) {
    this.passwordService = passwordService;
  }

  public interface NavigateOK {
    void navigate();
  }

  private NavigateOK navigateOK;

  public void addNavigate(NavigateOK navigateOK) {
    this.navigateOK = navigateOK;
  }


  public MyComponent() {

    getContent().add(username, password, myButton);

    myButton.addClickListener(e -> {
      String user   = username.getValue();
      String passwd = password.getValue();

      Boolean isOK = passwordService.check(user, passwd);

      if (isOK) {
        navigateOK.navigate();
      } else {
        navigateFalse();
      }

    });


  }

  private void navigateFalse() {
    username.setValue(null);
    username.setPlaceholder("fill in User Name");
    password.setValue(null);
  }
}
