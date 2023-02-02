package com.example.motorbreedfinal.controller;

import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.dao.CustomizeProfileDAO;
import com.example.motorbreedfinal.model.dao.ResearchDAO;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.model.users.Seller;
import com.example.motorbreedfinal.view1.ModifyAdControllerG;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.CarBean;

public class ManageAdsController {

    ResearchDAO researchDAO;

    Seller seller;

    AdBean adBean;

    int index = 0;

    public void setModifyAdControllerG(ModifyAdControllerG modifyAdControllerG){
        this.modifyAdControllerG = modifyAdControllerG;
        modifyAdControllerG.setManageAdsController(this);
    }

    ModifyAdControllerG modifyAdControllerG;

    public void retrieveMyAds(){
        researchDAO = new ResearchDAO();

        seller = LoggedUser.getInstance().getSeller();

        seller.setAdList(researchDAO.findSellerAds(seller.getIdAccount()));

        adBean = new AdBean();
        adBean.setAds(seller.getAdList());

    }

    public void showAd(int index) {
        modifyAdControllerG.showAd(adBean, index);
    }

    public void showNextAd() {
        this.index++;
        if(this.index >= seller.getAdList().toArray().length|| this.index == -1) {
            this.index = 0;
        }
        modifyAdControllerG.showAd(adBean, index);
    }

    public void showPreviousAd() {
        this.index--;
        if(this.index >= seller.getAdList().toArray().length|| this.index == -1) {
            this.index = 0;
        }
        modifyAdControllerG.showAd(adBean, index);
    }

    public void modifyAd(AdBean adBean, CarBean carBean, int idCar, String decorations) {
        Ad ad = new Ad();
        ad.setPriceCertification(adBean.isPriceCertification());
        ad.setDescription(adBean.getDescription());
        ad.setCost(adBean.getCost());
        ad.setLocation(adBean.getLocation());
        ad.setSeller(LoggedUser.getInstance().getSeller());
        ad.setInsertionDate(adBean.getInsertionDate().toString());
        ad.setNumberOfClicks(adBean.getnumberOfClicks());
        ad.setIdAd(String.valueOf(adBean.getIdAd()));

        ad.getCar().setInsurance(carBean.isInsurance());
        ad.getCar().setIdCar(String.valueOf(idCar));
        ad.getCar().setImmatricolationYear(carBean.getImmatricolationYear());
        ad.getCar().setTransmission(carBean.getTransmission());
        ad.getCar().setMileage(carBean.getMileage());
        ad.getCar().setHorsepower(carBean.getHorsepower());
        ad.getCar().setProductionYear(carBean.getProductionYear());
        ad.getCar().setDecorations(decorations);
        ad.getCar().setBrand(carBean.getBrand());
        ad.getCar().setModel(carBean.getModel());
        ad.getCar().setLicencePlate(carBean.getLicencePlate());
        ad.getCar().setIdCar(String.valueOf(idCar));
        ad.getCar().setFuelType(carBean.getFuelType());

        CustomizeProfileDAO customizeProfileDAO = new CustomizeProfileDAO();
        customizeProfileDAO.modifyAd(ad);
    }

    public AdBean getAdBean() {
        return adBean;
    }
}
