package sample;



import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;




import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class Main extends Application {
    Continent asia= new Continent("Asia"),europe= new Continent("Europe"),africa = new Continent("Africa"), america= new Continent("America"),oceania = new Continent("Oceania");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Networking networking = new Networking();
        networking.getData();

        try
        {


            File file = new File(System.getProperty("user.dir")+"\\data.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("record");

            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);

                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;

                    String popData = eElement.getElementsByTagName("popData2018").item(0).getTextContent();
                    String countriesAndTerritories = eElement.getElementsByTagName("countriesAndTerritories").item(0).getTextContent();
                    String cases = eElement.getElementsByTagName("cases").item(0).getTextContent();
                    String deaths = eElement.getElementsByTagName("deaths").item(0).getTextContent();
                    String dateRep = eElement.getElementsByTagName("dateRep").item(0).getTextContent();

                    if(eElement.getElementsByTagName("continentExp").item(0).getTextContent().equals("Asia")){
                        if (popData.equals("")){
                            popData = "0";
                        }
                        asia.addData(popData, countriesAndTerritories,cases, deaths,dateRep);
                    }else if(eElement.getElementsByTagName("continentExp").item(0).getTextContent().equals("Europe")){
                        if (popData.equals("")){
                            popData = "0";
                        }
                        europe.addData(popData, countriesAndTerritories,cases, deaths,dateRep);
                    }else if(eElement.getElementsByTagName("continentExp").item(0).getTextContent().equals("Africa")){
                        if (popData.equals("")){
                            popData = "0";
                        }
                        africa.addData(popData, countriesAndTerritories,cases, deaths,dateRep);
                    }else if(eElement.getElementsByTagName("continentExp").item(0).getTextContent().equals("America")){
                        if (popData.equals("")){
                            popData = "0";
                        }
                        america.addData(popData, countriesAndTerritories,cases, deaths,dateRep);
                    }else if(eElement.getElementsByTagName("continentExp").item(0).getTextContent().equals("Oceania")){
                        if (popData.equals("")){
                            popData = "0";
                        }
                        oceania.addData(popData, countriesAndTerritories,cases, deaths,dateRep);
                    }

                }

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        TableView tableView = new TableView();

        TableColumn<String, Country> column1 = new TableColumn<>("Country");
        column1.setCellValueFactory(new PropertyValueFactory<>("countryName"));

        TableColumn<Integer, Country> column2 = new TableColumn<>("Total Cases");
        column2.setCellValueFactory(new PropertyValueFactory<>("totalCases"));

        TableColumn<Integer, Country> column3 = new TableColumn<>("New Cases");
        column3.setCellValueFactory(new PropertyValueFactory<>("newCases"));

        TableColumn<Integer, Country> column4 = new TableColumn<>("Total Deaths");
        column4.setCellValueFactory(new PropertyValueFactory<>("totalDeaths"));

        TableColumn<Integer, Country> column5 = new TableColumn<>("New Deaths");
        column5.setCellValueFactory(new PropertyValueFactory<>("newDeaths"));

        TableColumn<String, Country> column6 = new TableColumn<>("Population");
        column6.setCellValueFactory(new PropertyValueFactory<>("population"));

        TableColumn<Double, Country> column7 = new TableColumn<>("Mortality");
        column7.setCellValueFactory(new PropertyValueFactory<>("mortality"));

        TableColumn<Double, Country> column8 = new TableColumn<>("Attack Rate");
        column8.setCellValueFactory(new PropertyValueFactory<>("attackRate"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);
        tableView.getColumns().add(column7);
        tableView.getColumns().add(column8);

        for (int i = 0 ; i < asia.countries.size(); i++){
            tableView.getItems().add(new Country(asia.countries.get(i).countriesAndTerritories,asia.countries.get(i).totalCases,asia.countries.get(i).newCases,asia.countries.get(i).totalDeaths,asia.countries.get(i).newDeaths,asia.countries.get(i).popData2018,asia.countries.get(i).mortality,asia.countries.get(i).attackRate));
        }
        for (int i = 0 ; i < europe.countries.size(); i++){
            tableView.getItems().add(new Country(europe.countries.get(i).countriesAndTerritories,europe.countries.get(i).totalCases,europe.countries.get(i).newCases,europe.countries.get(i).totalDeaths,europe.countries.get(i).newDeaths,europe.countries.get(i).popData2018,europe.countries.get(i).mortality,europe.countries.get(i).attackRate));
        }
        for (int i = 0 ; i < africa.countries.size(); i++){
            tableView.getItems().add(new Country(africa.countries.get(i).countriesAndTerritories,africa.countries.get(i).totalCases,africa.countries.get(i).newCases,africa.countries.get(i).totalDeaths,africa.countries.get(i).newDeaths,africa.countries.get(i).popData2018,africa.countries.get(i).mortality,africa.countries.get(i).attackRate));
        }
        for (int i = 0 ; i < america.countries.size(); i++){
            tableView.getItems().add(new Country(america.countries.get(i).countriesAndTerritories,america.countries.get(i).totalCases,america.countries.get(i).newCases,america.countries.get(i).totalDeaths,america.countries.get(i).newDeaths,america.countries.get(i).popData2018,america.countries.get(i).mortality,america.countries.get(i).attackRate));
        }
        for (int i = 0 ; i < oceania.countries.size(); i++){
            tableView.getItems().add(new Country(oceania.countries.get(i).countriesAndTerritories,oceania.countries.get(i).totalCases,oceania.countries.get(i).newCases,oceania.countries.get(i).totalDeaths,oceania.countries.get(i).newDeaths,oceania.countries.get(i).popData2018,oceania.countries.get(i).mortality,oceania.countries.get(i).attackRate));
        }



        VBox vbox = new VBox(tableView);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);

        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
