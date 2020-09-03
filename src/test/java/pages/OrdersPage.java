package pages;

import components.commons.TableComponent;

public class OrdersPage extends BaseWappiPage {

    public OrdersPage(){
    }

    public TableComponent getOrdersTableComponent() {
        return new TableComponent(tableComponentContainer);
    }
}
