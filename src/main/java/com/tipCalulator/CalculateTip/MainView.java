package com.tipCalulator.CalculateTip;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;



import java.math.BigDecimal;

@Route(value = "")
public class MainView extends VerticalLayout {

    private Label headerLabel;
    private TextField bill ;
    private TextField billSplit ;
    private TextField tip;
    private Button button;
    private Label displayTip;

    public MainView (){

        setUpLayout();
    }


    public void setUpLayout(){
        headerLabel = new Label("Shared Tip Calculator");
        headerLabel.getStyle().set("fontWeight", "bold");

        bill = new TextField("How much was your bill £");
        bill.setRequired(true);

        tip = new TextField("Percentage of tip");
        tip.setRequired(true);

        displayTip = new Label("");

        billSplit = new TextField("How Many People ?");

        button = new Button("Calculate Tip");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener( e -> {
            if (bill.isEmpty()){
                Notification.show("Please Enter Bill Amount");
            }
            else {
                CalculateTip();
            }
        });

        add(headerLabel,bill,tip,billSplit,button,displayTip);

        setAlignItems(Alignment.CENTER);
    }

    public void CalculateTip()
    {
        double billAmount = Double.parseDouble(bill.getValue().trim());
        double percentage = Double.parseDouble(tip.getValue().trim());
        double numOfPeople = Double.parseDouble(billSplit.getValue().trim());


            double total = (billAmount * percentage)/numOfPeople;
            total = Math.round(total * 100)/100;
            BigDecimal bigDecimal = new BigDecimal(total);
            BigDecimal newValue  = bigDecimal.movePointLeft(2);
            System.out.println(newValue);

            displayTip.setText("Each Person Tips £" + newValue);




    }
}
