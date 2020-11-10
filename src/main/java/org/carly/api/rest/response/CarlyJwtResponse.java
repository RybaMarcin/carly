package org.carly.api.rest.response;

public class CarlyJwtResponse {

    private String carlyJwt;

    public CarlyJwtResponse(String carlyJwt) {
        this.carlyJwt = carlyJwt;
    }

    public String getCarlyJwt() {
        return carlyJwt;
    }

    public void setCarlyJwt(String carlyJwt) {
        this.carlyJwt = carlyJwt;
    }
}
