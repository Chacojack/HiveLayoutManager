package jack.hive;

/**
 * Created by zjchai on 16/9/11.
 */
class HivePositionInfo {

    int floor ;
    int position ;

    public HivePositionInfo() {
    }

    HivePositionInfo(int floor, int position) {
        this.floor = floor;
        this.position = position;
    }

    @Override
    public String toString() {
        return "HivePositionInfo{" +
                "floor=" + floor +
                ", position=" + position +
                '}';
    }
}
