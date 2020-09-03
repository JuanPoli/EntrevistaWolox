package pages;

import components.commons.TableComponent;

public class HomePage extends BaseWappiPage {

    public HomePage() {
        super();
    }

    public TableComponent getOffersTableComponent() {
        return new TableComponent(tableComponentContainer);
    }
}
