package DroneController;

import lombok.Getter;

public class DroneRequest {
    @Getter
    private Double startX;

    @Getter
    private Double startY;

    @Getter
    private Double destinationX;

    @Getter
    private Double destinationY;

    @Getter
    private Integer capacity;
}
