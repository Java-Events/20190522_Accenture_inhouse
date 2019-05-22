package org.rapidpm.vaadin.v10.tb.demo;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;

import java.util.Set;
import java.util.function.Consumer;

import static java.util.concurrent.ConcurrentHashMap.newKeySet;

public class ChildComponent extends Composite<VerticalLayout> {

  private final TextField input = new TextField();
  private final Button    pressMe = new Button();

  public ChildComponent() {

    pressMe.addClickListener(e -> {
      consumers.forEach(c -> c.accept(input.getValue()));
    });

    getContent().add(input, pressMe);
  }

  private Set<Registration> registrations = newKeySet();
  private Set<Consumer<String>> consumers = newKeySet();

  public Registration registerAsConsumer(Consumer<String> valueConsumer) {

    consumers.add(valueConsumer);

    return (Registration) () -> {
      registrations.remove(valueConsumer);
      consumers.remove(valueConsumer);

    };
  }
}
