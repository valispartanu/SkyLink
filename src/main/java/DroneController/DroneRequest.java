package DroneController;

import lombok.Getter;

public class DroneRequest {
    @Getter
    private Float startX;

    @Getter
    private Float startY;

    @Getter
    private Float destinationX;

    @Getter
    private Float destinationY;
}
