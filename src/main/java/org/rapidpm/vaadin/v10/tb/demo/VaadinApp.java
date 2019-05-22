/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rapidpm.vaadin.v10.tb.demo;


import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import org.rapidpm.dependencies.core.logger.HasLogger;

import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Optional.ofNullable;
import static org.rapidpm.vaadin.addon.idgenerator.VaadinIDGenerator.buttonID;
import static org.rapidpm.vaadin.v10.tb.demo.VaadinApp.NAV;
//import static org.rapidpm.vaadin.addons.framework.ComponentIDGenerator.buttonID;
//import static org.rapidpm.vaadin.addons.framework.ComponentIDGenerator.spanID;

@Route(NAV)
//@PageTitle("jsrgfkjargt")
public class VaadinApp
    extends Composite<Div>
    implements HasLogger {
  public static final String NAV = "";

  // read http://vaadin.com/testing for more infos
  public static final String BTN_CLICK_ME = buttonID().apply(VaadinApp.class, "btn-click-me");
//  public static final String LB_CLICK_COUNT = spanID().apply(VaadinApp.class, "lb-click-count");

//  private final Button         btnClickMe   = new Button(getTranslation("button.label.key"));
  private final Button         btnClickMe   = new Button("press Me");
  private final Span           lbClickCount = new Span("0");

  private final Span fromChild = new Span("fromChild");

  private final ChildComponent childComponent = new ChildComponent();

  private final VerticalLayout layout       = new VerticalLayout(btnClickMe,
                                                                 lbClickCount,
                                                                 childComponent,
                                                                 fromChild);

//

  //@Inject ...
  private final Service                service = new MyService();
  private       Optional<Registration> registration = Optional.empty();

  public VaadinApp() {
    btnClickMe.setId(BTN_CLICK_ME);
    btnClickMe.addClickListener(event -> lbClickCount.setText(service.doSomeWork()));


    //UI.getCurrent().access()
//    lbClickCount.setId(LB_CLICK_COUNT);

    //set the main Component
    logger().info("setting now the main ui content..");

//    MyComponent myComponent = new MyComponent();
//    myComponent.addNavigate(() -> Notification.show("Huhu"));
//    myComponent.usePasswordService((u,p) -> true);

     registration = ofNullable(childComponent.registerAsConsumer(fromChild::setText));

    Div content = getContent();
    content.add(layout);

  }

  @Override
  protected void onDetach(DetachEvent detachEvent) {
    super.onDetach(detachEvent);
    registration.ifPresent(Registration::remove);
  }
}
