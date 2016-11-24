package jack.hive;

/**
 * Created by zjchai on 16/9/11.
 */
class HivePositionInfo {

    int mFloor;
    int mPosition;

    public HivePositionInfo() {
    }

    HivePositionInfo(int floor, int position) {
        this.mFloor = floor;
        this.mPosition = position;
    }

    @Override
    public String toString() {
        return "HivePositionInfo{" +
                "mFloor=" + mFloor +
                ", mPosition=" + mPosition +
                '}';
    }
}
