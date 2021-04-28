package org.carly.core.partsmanagement.service;

import org.carly.api.rest.request.PartDetailsRequest;
import org.carly.api.rest.response.PartDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PartsFindService {

    private static final String BRAKES = "Brakes";
    private static final String ENGINE = "Engine";
    private static final String EQUIPMENT = "Equipment";
    private static final String PAINTING = "Painting";
    private static final String TIRES = "Tires";
    private static final String WHEELS = "Wheels";
    private static final String WINDOWS = "Windows";

    private final BrakeFindService brakesFindService;
    private final EngineFindService engineFindService;
    private final EquipmentFindService equipmentFindService;
    private final PaintingFindService paintingFindService;
    private final TiresFindService tiresFindService;
    private final WheelsFindService wheelsFindService;
    private final WindowsFindService windowsFindService;

    public PartsFindService(BrakeFindService brakesFindService,
                            EngineFindService engineFindService,
                            EquipmentFindService equipmentFindService,
                            PaintingFindService paintingFindService,
                            TiresFindService tiresFindService,
                            WheelsFindService wheelsFindService,
                            WindowsFindService windowsFindService) {
        this.brakesFindService = brakesFindService;
        this.engineFindService = engineFindService;
        this.equipmentFindService = equipmentFindService;
        this.paintingFindService = paintingFindService;
        this.tiresFindService = tiresFindService;
        this.wheelsFindService = wheelsFindService;
        this.windowsFindService = windowsFindService;
    }

    public ResponseEntity<?> findFactoryParts(String factoryId) {
        return null;
    }

    public ResponseEntity<?> findPartDetails(PartDetailsRequest request) {
        PartDetailsResponse partDetails = new PartDetailsResponse();
        switch(request.getPartType()) {
            case BRAKES:
                partDetails = brakesFindService.findBrakesDetails(request.getPartId());
                break;
            case ENGINE:

            case EQUIPMENT:

            case PAINTING:

            case TIRES:

            case WHEELS:

            case WINDOWS:

            default:
                break;
        }
        return ResponseEntity.ok(partDetails);
    }

}
