package View;

import Model.OrderData;
import Presenter.OrdersPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

// view pro obrazovku objednávek, předává data presenteru
public class OrdersView extends View implements Initializable {

    private OrdersPresenter presenter;

    @FXML
    private TableView<OrderData> table;
    @FXML
    public TableColumn<OrderData, String> orderNumber;

    @FXML
    public TableColumn<OrderData, String> orderDate;

    @FXML
    public TableColumn<OrderData, String> productName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new OrdersPresenter(this);

        orderNumber.setCellValueFactory(new PropertyValueFactory<OrderData, String>("orderNumber"));
        orderDate.setCellValueFactory(new PropertyValueFactory<OrderData, String>("orderDate"));
        productName.setCellValueFactory(new PropertyValueFactory<OrderData, String>("productName"));
        presenter.getOrders(false);
    }

    // metoda volaná po stisknutí tlačítka refresh, volá metodu z presenteru
    public void refreshOrders(){
        presenter.getOrders(true);
    }

    // plní sloupečky datama v tableView objednávek
    public void setTableData(List<OrderData> orders) {
        ObservableList<OrderData> list = FXCollections.observableArrayList();
        list.addAll(orders);
        table.getItems().clear();
        table.setItems(list);
    }
}
