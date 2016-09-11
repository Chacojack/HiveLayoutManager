package jack.hive;

/**
 * Created by zjchai on 16/9/11.
 */
public class HivePositionInfo {

    int floor ;
    int position ;

    public HivePositionInfo() {
    }

    public HivePositionInfo(int floor, int position) {
        this.floor = floor;
        this.position = position;
    }

    public int getFloor() {
        return floor;
    }

    public HivePositionInfo setFloor(int floor) {
        this.floor = floor;
        return this ;
    }

    public int getPosition() {
        return position;
    }

    public HivePositionInfo setPosition(int position) {
        this.position = position;
        return this ;
    }

    @Override
    public String toString() {
        return "HivePositionInfo{" +
                "floor=" + floor +
                ", position=" + position +
                '}';
    }
}
