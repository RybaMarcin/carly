package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.*;
import org.carly.vehicle_management.core.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.carly.shared.utils.builder.Builder.anObject;
import static org.carly.support.Bodies.aBody1;
import static org.carly.support.Brands.aBrand1;
import static org.carly.support.Equipments.aEquipment1;
import static org.carly.support.Equipments.aEquipment2;
import static org.carly.support.Models.aModel1;
import static org.carly.support.Paintings.aPainting1;
import static org.carly.support.TiresModel.aTires1;
import static org.carly.support.WheelsModel.aWheels1;
import static org.carly.support.WindowsModel.aWindows1;

public class Cars {

    public static final ObjectId CAR_ID_1 = new ObjectId("5da1cd86be0ad871841e9034");
    public static final LocalDate CREATE_AT_1 = LocalDate.of(2019, 5, 22);
    public static final LocalDate MODIFY_AT_1 = LocalDate.of(2019, 8, 12);
    public static final String VIN_NUMBER_1 = "JH4DA1850GS002669";
    public static final ChangeRequestStatus CHANGE_REQUEST_STATUS_1 = ChangeRequestStatus.ACCEPTED;
    public static final String NAME_1 = "Opel";
    public static final String CODE_1 = "g776sx";
    public static final Brand BRAND_1 = aBrand1();
    public static final Model MODEL_1 = aModel1();
    public static final double MAX_SPEED_1 = 220.0;
    public static final double ACCELERATE_1 = 7.5;
    public static final BigDecimal PRICE_1 = new BigDecimal(88999.99);
    public static final LocalDate YEAR_OF_PRODUCTION_1 = LocalDate.of(2019, 3, 8);
    public static final Transmission TRANSMISSION_1 = Transmission.MANUAL;
    public static final Tires TIRES_1 = aTires1();
    public static final Wheels WHEELS_1 = aWheels1();
    public static final Windows WINDOWS_1 = aWindows1();
    public static final Body BODY_1 = aBody1();
    public static final double WEIGHT_1 = 1256.66;
    public static final int NUMBER_OF_DOORS_1 = 5;
    public static final Painting BODY_PAINTING_1 = aPainting1();
    public static final List<Equipment> EQUIPMENTS_1 = List.of(aEquipment1(), aEquipment2());

    public static Car aCar1() {
        return anObject(Car.class)
                .with(c -> c.setId(CAR_ID_1))
                .with(c -> c.setCreateAt(CREATE_AT_1))
                .with(c -> c.setModifyAt(MODIFY_AT_1))
                .with(c -> c.setVinNumber(VIN_NUMBER_1))
                .with(c -> c.setRequestStatus(CHANGE_REQUEST_STATUS_1))
                .with(c -> c.setName(NAME_1))
                .with(c -> c.setCode(CODE_1))
                .with(c -> c.setBrand(BRAND_1))
                .with(c -> c.setModel(MODEL_1))
                .with(c -> c.setMaxSpeed(MAX_SPEED_1))
                .with(c -> c.setAccelerate(ACCELERATE_1))
                .with(c -> c.setPrice(PRICE_1))
                .with(c -> c.setYearOfProduction(YEAR_OF_PRODUCTION_1))
                .with(c -> c.setTransmission(TRANSMISSION_1))
                .with(c -> c.setTires(TIRES_1))
                .with(c -> c.setWheels(WHEELS_1))
                .with(c -> c.setWindows(WINDOWS_1))
                .with(c -> c.setBody(BODY_1))
                .with(c -> c.setWeight(WEIGHT_1))
                .with(c -> c.setNumberOfDoors(NUMBER_OF_DOORS_1))
                .with(c -> c.setBodyPainting(BODY_PAINTING_1))
                .with(c -> c.setEquipment(EQUIPMENTS_1))
                .build();
    }
}