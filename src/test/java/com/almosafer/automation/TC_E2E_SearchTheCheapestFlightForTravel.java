package com.almosafer.automation;

import com.almosafer.automation.base.BaseTest;
import com.almosafer.automation.elementRepositories.*;
import org.testng.annotations.Test;


import java.util.logging.Logger;

import static com.almosafer.automation.utils.JsonParser.*;

public class TC_E2E_SearchTheCheapestFlightForTravel extends BaseTest {
    public static final Logger logger=Logger.getLogger(TC_E2E_SearchTheCheapestFlightForTravel.class.getName());

    @Test (description = "Almosafer Native app: Validation for the whole process of a traveler/airline interaction.")
    public void verifyAndSearchTheCheapestFlightForTravel() {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        HomeScreen homeScreen = new HomeScreen();
        SearchFlightsScreen searchFlightsScreen = new SearchFlightsScreen();
        SearchOriginScreen searchOriginScreen = new SearchOriginScreen();
        SearchDestinationScreen searchDestinationScreen = new SearchDestinationScreen();
        SelectDateScreen selectDateScreen = new SelectDateScreen();
        PaxAndCabinScreen paxAndCabinScreen = new PaxAndCabinScreen();
        FlightDetailsScreen flightDetailsScreen = new FlightDetailsScreen();
        FiltersScreen filtersScreen = new FiltersScreen();

        String depDate = parseJsonObjToStr(flightDetails,"flightDetails","departureDate");
        String depMonth = parseJsonObjToStr(flightDetails,"flightDetails","departureMonth");
        String depYear = parseJsonObjToStr(flightDetails,"flightDetails","departureYear");
        String retDate = parseJsonObjToStr(flightDetails,"flightDetails","returnDate");
        String retMonth = parseJsonObjToStr(flightDetails,"flightDetails","returnMonth");
        String retYear = parseJsonObjToStr(flightDetails,"flightDetails","returnYear");

        logger.info("Scenario 1: Select Language and Country");
        welcomeScreen.verifyAndClickLanguage();
        welcomeScreen.verifyScreenTitle();
        welcomeScreen.verifyAndSelectCounty(parseJsonObjToStr(flightDetails,"flightDetails","country"));
        welcomeScreen.clickContinue();

        logger.info("Scenario 2: Navigate to flights-home screen");
        homeScreen.clickOnWelcomePopup();
        homeScreen.verifyHomeScreenTitle();
        homeScreen.clickFlightOrHotelOptions(parseJsonObjToStr(flightDetails,"flightDetails","businessOption"));

        logger.info("Scenario 3: Enter From and To Airport code along with mandatory validations");
        searchFlightsScreen.verifySearchScreenTitle();
        searchFlightsScreen.verifyRoundTripAndSelectIfNot();
        searchFlightsScreen.clickFromOption();
        searchOriginScreen.verifySearchOriginTitle();
        searchOriginScreen.enterAirportCodeInSearch(parseJsonArrToStringArr(flightDetails,"flightDetails","originAirCode"));
        searchOriginScreen.verifyAndClickOriginAirCode();
        searchDestinationScreen.verifySearchDestinationTitle();
        searchDestinationScreen.enterAirportCodeInSearch(parseJsonArrToStringArr(flightDetails,"flightDetails","destinationAirCode"));
        searchDestinationScreen.verifyAndClickDestinationAirCode();
        searchFlightsScreen.verifySelectedAirportLocationAndCode();

        logger.info("Scenario 4: Select the Departure and Return date along with mandatory validations");
        searchFlightsScreen.verifyDefaultDepartureDate();
        searchFlightsScreen.verifyDefaultReturnDate();
        searchFlightsScreen.clickDepartureDateLabel();
        selectDateScreen.verifySelectDateScreenTitle();
        selectDateScreen.selectTravelDepartureDate(depDate,depMonth,depYear);
        selectDateScreen.verifySelectReturnDateText();
        selectDateScreen.selectTravelReturnDate(retDate,retMonth,retYear);
        selectDateScreen.verifySelectedTravelDates();
        selectDateScreen.verifyConfirmAndSelect();
        searchFlightsScreen.verifySelectedDepartureDate();
        searchFlightsScreen.verifySelectedReturnDate();

        logger.info("Scenario 5: Add the number of travellers along with mandatory validations");
        searchFlightsScreen.verifyDefaultPaxAndCabin();
        searchFlightsScreen.clickPaxAndCabin();
        paxAndCabinScreen.verifyPaxAndCabinTitle();
        paxAndCabinScreen.verifyAdultsAndAddNumOfAdults(parseJsonObjToStr(flightDetails,"flightDetails","adultsCount"));
        paxAndCabinScreen.verifyChildrenAndAddNumOfChildren(parseJsonObjToStr(flightDetails,"flightDetails","childrenCount"));
        paxAndCabinScreen.verifyInfantsAndAddNumOfInfant(parseJsonObjToStr(flightDetails,"flightDetails","infantsCount"));
        paxAndCabinScreen.clickCabinClass(parseJsonObjToStr(flightDetails,"flightDetails","cabinClass"));
        paxAndCabinScreen.clickApply();
        searchFlightsScreen.verifySelectedTravellersTotalCountAndCabin();

        logger.info("Scenario 6: Navigate to Flight Details screen and do the mandatory validations");
        searchFlightsScreen.clickFindFlights();
        flightDetailsScreen.verifyFlightDetailTitle();
        flightDetailsScreen.verifyTravellerAndDates();

        logger.info("Scenario 7: Sort the lowest price and compare with first flight fare with starting price");
        flightDetailsScreen.verifyAndClickSortBy();
        flightDetailsScreen.verifyAndClickLowestPrice();
        flightDetailsScreen.verifyLowestPriceInFilter();
        flightDetailsScreen.fetchAndSaveCheapestFlight();
        flightDetailsScreen.compareFirstCheapestFlightWithMultipleFlights();
        flightDetailsScreen.verifyAndClickFilters();
        filtersScreen.verifyFiltersScreenTitle();
        filtersScreen.verifyStartingPriceWithLowestPrice();
    }
}
