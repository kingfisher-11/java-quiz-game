/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Quote;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class QuoteOverviewCtrl implements Initializable {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    private ObservableList<Quote> data;

    @FXML
    private TableView<Quote> table;
    @FXML
    private TableColumn<Quote, String> colFirstName;
    @FXML
    private TableColumn<Quote, String> colLastName;
    @FXML
    private TableColumn<Quote, String> colQuote;

    /**
     * Constructor for QuoteOverviewCtrl
     *
     * @param server   the ServerUtils to be used
     * @param mainCtrl the MainCtrl to be used
     */
    @Inject
    public QuoteOverviewCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }

    /**
     * Initializes th quote overview
     *
     * @param location  the URL to be used
     * @param resources the ResourceBundle to be used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colFirstName.setCellValueFactory(q -> new SimpleStringProperty(q.getValue().person.firstName));
        colLastName.setCellValueFactory(q -> new SimpleStringProperty(q.getValue().person.lastName));
        colQuote.setCellValueFactory(q -> new SimpleStringProperty(q.getValue().quote));
    }

    /**
     * Calls method showAdd
     */
    public void addQuote() {
//        mainCtrl.showAdd();
    }

    /**
     * Refreshes the page with all the quotes known to the server
     */
    public void refresh() {
        var quotes = server.getQuotes();
        data = FXCollections.observableList(quotes);
        table.setItems(data);
    }
}