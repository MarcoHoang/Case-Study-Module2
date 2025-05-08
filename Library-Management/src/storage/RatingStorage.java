package storage;

import config.AppConfig;
import model.Rating;
import java.util.List;

public class RatingStorage extends GenericFileStorage<Rating> {
    public List<Rating> getAllRatings() {
        return readFromFile(AppConfig.RATING_FILE);
    }

    public void saveAllRatings(List<Rating> ratings) {
        writeToFile(AppConfig.RATING_FILE, ratings);
    }
}
