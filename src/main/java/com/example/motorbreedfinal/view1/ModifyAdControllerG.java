package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.ManageAdsController;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.CarBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ModifyAdControllerG {
    @FXML
    private CheckBox cruiseBox;

    @FXML
    private CheckBox ledBox;

    @FXML
    private Rectangle background;

    @FXML
    private TextField brandTF;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField matriculationYearTF;

    @FXML
    private Button editButton;

    @FXML
    private TextField fuelTF;

    @FXML
    private CheckBox heatedSeatsBox;

    @FXML
    private Button homepageButton;

    @FXML
    private TextField hpTF;

    @FXML
    private CheckBox keyBox;

    @FXML
    private TextField locationTF;

    @FXML
    private TextField mileageTF;

    @FXML
    private TextField modelTF;

    @FXML
    private Label certificationLabel;

    @FXML
    private ImageView icon;

    @FXML
    private Button nextButton;

    @FXML
    private TextField pYearTF;

    @FXML
    private CheckBox parkingSensorsBox;



    @FXML
    private Button previousButton;

    @FXML
    private TextField priceTF;

    @FXML
    private TextField insertionDateTF;

    @FXML
    private Label sellerLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Button deleteAdButton;

    @FXML
    private Button editDescButton;

    @FXML
    private TextField descriptionTF;


    @FXML
    private CheckBox insuranceCheckBox;


    @FXML
    private Button closePaneButton;

    @FXML
    private Label oneOfLabel;

    @FXML
    private Label soldLabel;
    @FXML
    private Button changePicButton;

    @FXML
    private TextField transmissionTF;

    @FXML
    private Pane descPane;

    ManageAdsController manageAdsController;

    int previousPrice = 0;

    int idAd = 0;

    AdBean myAdbean;

    int numberClicks = 0;

    int idCar = 0;

    String decorations = "";

    String licencePlate = "";

    public void setManageAdsController(ManageAdsController manageAdsController){
        this.manageAdsController = manageAdsController;
    }

    public void showAd(AdBean adBean, int index){
        myAdbean = adBean;

        this.priceTF.setText((adBean.getAds().get(index).getAdCost())+" €");
        this.transmissionTF.setText(adBean.getAds().get(index).getCar().getTransmission());
        this.brandTF.setText(adBean.getAds().get(index).getCar().getBrand());
        this.modelTF.setText(adBean.getAds().get(index).getCar().getModel());
        this.sellerLabel.setText(LoggedUser.getInstance().getSeller().getFirstName()+" "+LoggedUser.getInstance().getSeller().getLastName());
        this.fuelTF.setText(adBean.getAds().get(index).getCar().getFuelType());
        this.hpTF.setText((adBean.getAds().get(index).getCar().getHorsepower())+" cv");
        this.pYearTF.setText(adBean.getAds().get(index).getCar().getProductionYear());
        this.mileageTF.setText((adBean.getAds().get(index).getCar().getMileage())+" km");
        this.matriculationYearTF.setText(adBean.getAds().get(index).getCar().getImmatricolationYear());
        this.insertionDateTF.setText(adBean.getAds().get(index).getInsertionDate());
        this.descriptionTF.setText(adBean.getAds().get(index).getAdDescription());
        this.oneOfLabel.setText(index+1 + " of " + adBean.getAds().toArray().length);
        previousPrice = adBean.getAds().get(index).getAdCost();
        idAd = Integer.parseInt(adBean.getAds().get(index).getIdAd());
        numberClicks = adBean.getAds().get(index).getNumberOfClicks();
        idCar = Integer.parseInt(adBean.getAds().get(index).getCar().getIdCar());
        licencePlate = adBean.getAds().get(index).getCar().getLicencePlate();

        if(!adBean.getAds().get(index).isPriceCertificated()){
            this.icon.setVisible(false);
            this.certificationLabel.setVisible(false);
        }else {
            this.icon.setVisible(true);
            this.certificationLabel.setVisible(true);
        }


        if(adBean.getAds().get(index).getCar().getDecorations().charAt(0) == '1') {
            this.cruiseBox.setSelected(true);
        }
        if(adBean.getAds().get(index).getCar().getDecorations().charAt(1) == '1') {
            this.keyBox.setSelected(true);
        }
        if(adBean.getAds().get(index).getCar().getDecorations().charAt(2) == '1') {
            this.heatedSeatsBox.setSelected(true);
        }
        if(adBean.getAds().get(index).getCar().getDecorations().charAt(3) =='1') {
            this.ledBox.setSelected(true);
        }
        if(adBean.getAds().get(index).getCar().getDecorations().charAt(4) == '1') {
            this.parkingSensorsBox.setSelected(true);
        }
    }


    @FXML
    void confirmChanges(ActionEvent event) {

        AdBean adBean = new AdBean();

        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        adBean.setInsertionDate(out);

        adBean.setNumberOfClicks(numberClicks);

        adBean.setIdAd(String.valueOf(idAd));

        extractLocation(adBean);

        adBean.setCost(Integer.parseInt(this.priceTF.getText().substring(0, this.priceTF.getText().length()-2)));

        if(Integer.parseInt(this.priceTF.getText().substring(0, this.priceTF.getText().length()-2)) != previousPrice){
            adBean.setPriceCertification(false);
        }

        extractDescription(adBean);

        CarBean carBean = new CarBean();

        carBean.setInsurance(insuranceCheckBox.isSelected());

        extractBrand(carBean);

        extractModel(carBean);

        extractImmatricolationYear(carBean);

        extractMileage(carBean);

        extractHP(carBean);

        extractTransmission(carBean);

        extractProductionYear(carBean);

        extractFuelType(carBean);

        if(this.cruiseBox.isSelected()){
            decorations = decorations.concat("1");
        }else {
            decorations =decorations.concat("0");
        }
        if(this.keyBox.isSelected()){
            decorations =decorations.concat("1");
        }else {
            decorations =decorations.concat("0");
        }
        if(this.heatedSeatsBox.isSelected()){
            decorations =decorations.concat("1");
        }else {
            decorations =decorations.concat("0");
        }
        if(this.ledBox.isSelected()){
            decorations = decorations.concat("1");
        }else {
            decorations =decorations.concat("0");
        }
        if(this.parkingSensorsBox.isSelected()){
            decorations =decorations.concat("1");
        }else {
            decorations = decorations.concat("0");
        }

        carBean.setLicencePlate(licencePlate);


        manageAdsController.modifyAd(adBean, carBean, idCar, decorations);

        FXMLLoader fxmlLoader = FxmlLoader.setPage("SellerHomepage");
        SellerHomepageControllerG sellerHomepageControllerG = fxmlLoader.getController();
        sellerHomepageControllerG.setNameSurnameTF(LoggedUser.getInstance().getSeller().getFirstName(), (LoggedUser.getInstance().getSeller().getLastName()));
    }

    private void extractFuelType(CarBean carBean) {
        if(!this.fuelTF.getText().isEmpty()){
            carBean.setBeanFuelType(this.fuelTF.getText());
        }else{
            carBean.setBeanFuelType("");
        }
    }

    private void extractProductionYear(CarBean carBean) {
        if(!this.pYearTF.getText().isEmpty()){
            carBean.setBeanProductionYear(this.pYearTF.getText());
        }else {
            carBean.setBeanProductionYear("");
        }
    }

    private void extractTransmission(CarBean carBean) {
        if(!this.transmissionTF.getText().isEmpty()){
            carBean.setTransmission(this.transmissionTF.getText());
        }else{
            carBean.setTransmission("");
        }
    }

    private void extractHP(CarBean carBean) {
        if (!this.hpTF.getText().isEmpty()){
            carBean.setBeanHorsepower(Integer.parseInt(this.hpTF.getText().substring(0, this.hpTF.getText().length()-3)));
        }else {
            carBean.setBeanHorsepower(0);
        }
    }

    private void extractMileage(CarBean carBean) {
        if (!this.mileageTF.getText().isEmpty()){
            carBean.setCarBeanMileage(Integer.parseInt(this.mileageTF.getText().substring(0, this.mileageTF.getText().length()-3)));
        }else {
            carBean.setCarBeanMileage(0);
        }
    }

    private void extractImmatricolationYear(CarBean carBean) {
        if(!this.matriculationYearTF.getText().isEmpty()){
            carBean.setImmatricolationYear(this.matriculationYearTF.getText());
        }else {
            carBean.setImmatricolationYear("");
        }
    }

    private void extractModel(CarBean carBean) {
        if(!modelTF.getText().isEmpty()){
            carBean.setCarBeanModel(modelTF.getText());
        }else {
            carBean.setCarBeanModel("");
        }
    }

    private void extractBrand(CarBean carBean) {
        if(!brandTF.getText().isEmpty()) {
            carBean.setCarBeanBrand(brandTF.getText());
        }else {
            carBean.setCarBeanBrand("");
        }
    }

    private void extractDescription(AdBean adBean) {
        if(!descriptionTF.getText().isEmpty()){
            adBean.setDescription(this.descriptionTF.getText());
        }else {
            adBean.setDescription("");
        }
    }

    private void extractLocation(AdBean adBean) {
        if(!this.locationTF.getText().isEmpty()) {
            adBean.setLocation(this.locationTF.getText());
        }else {
            adBean.setLocation("");
        }
    }

    @FXML
    void editAd(ActionEvent event) {
        this.keyBox.setDisable(false);
        this.cruiseBox.setDisable(false);
        this.ledBox.setDisable(false);
        this.heatedSeatsBox.setDisable(false);
        this.parkingSensorsBox.setDisable(false);

        this.modelTF.setEditable(true);
        this.brandTF.setEditable(true);
        this.mileageTF.setEditable(true);
        this.hpTF.setEditable(true);
        this.transmissionTF.setEditable(true);
        this.pYearTF.setEditable(true);
        this.locationTF.setEditable(true);
        this.fuelTF.setEditable(true);
        this.priceTF.setEditable(true);
        this.matriculationYearTF.setEditable(true);

        this.confirmButton.setDisable(false);
        this.confirmButton.setVisible(true);

        this.descriptionTF.setEditable(true);

        this.insuranceCheckBox.setDisable(false);
    }

    void undoChanges(){
        this.keyBox.setDisable(true);
        this.cruiseBox.setDisable(true);
        this.ledBox.setDisable(true);
        this.heatedSeatsBox.setDisable(true);
        this.parkingSensorsBox.setDisable(true);

        this.keyBox.setSelected(false);
        this.cruiseBox.setSelected(false);
        this.ledBox.setSelected(false);
        this.heatedSeatsBox.setSelected(false);
        this.parkingSensorsBox.setSelected(false);

        this.modelTF.setEditable(false);
        this.brandTF.setEditable(false);
        this.mileageTF.setEditable(false);
        this.hpTF.setEditable(false);
        this.transmissionTF.setEditable(false);
        this.pYearTF.setEditable(false);
        this.locationTF.setEditable(false);
        this.fuelTF.setEditable(false);
        this.priceTF.setEditable(false);
        this.matriculationYearTF.setEditable(false);

        this.confirmButton.setDisable(true);
        this.confirmButton.setVisible(false);

        this.descriptionTF.setEditable(false);
        this.insuranceCheckBox.setDisable(true);
    }

    @FXML
    void goToHomepage(ActionEvent event) {
        FXMLLoader fxmlLoader = FxmlLoader.setPage("SellerHomepage");
        SellerHomepageControllerG sellerHomepageControllerG = fxmlLoader.getController();
        sellerHomepageControllerG.setNameSurnameTF(LoggedUser.getInstance().getSeller().getFirstName(), (LoggedUser.getInstance().getSeller().getLastName()));
    }

    @FXML
    void nextAd(ActionEvent event) {
        undoChanges();
        manageAdsController.showNextAd();
    }

    @FXML
    void previousAd(ActionEvent event) {
        undoChanges();
        manageAdsController.showPreviousAd();
    }

    @FXML
    void readDesc(ActionEvent event) {
        descPane.setDisable(false);
        descPane.setVisible(true);
    }

    @FXML
    void closedescPane(ActionEvent event) {
        descPane.setDisable(true);
        descPane.setVisible(false);
    }
}
